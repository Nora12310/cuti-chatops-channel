package app

import com.linecorp.bot.model.message.TextMessage
import datas.model.BuildMessage
import datas.model.PullRequestMessage
import datas.model.Response
import datas.model.gitlab.pineline.PipelineEvent
import datas.model.gitlab.pull_requests.PullRequest
import ext.buildDownloadLink
import ext.toJson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import repository.ILineEventService
import repository.LineRepository


@Controller
class GitlabWebHookCallback(
        private val repository: LineRepository,
        private val service: ILineEventService
) {
    private val log: Logger = LoggerFactory.getLogger(ILineEventService::class.java)

    @PostMapping("/gitlab/pipeline/callback")
    fun pipelineEvent(
            @RequestHeader(value = "X-Gitlab-Event") type: String,
            @RequestBody event: PipelineEvent
    ): ResponseEntity<*> {
        if (type == "Pipeline Hook") {
            log.info(event.toJson())
            val url = event.project.gitHttpUrl
            val status = event.objectAttributes.status ?: ""
            val project = repository.findProjectByUrl(url)
            project?.apply {
                val message = BuildMessage(
                        buildDate = event.commit.timestamp ?: "",
                        logs = event.commit.message ?: "",
                        tags = event.objectAttributes.ref ?: "",
                        downloadUrl = event.buildDownloadLink()
                )

                val messageString = when (status) {
                    "success" -> message.toString()
                    "running" -> "Your pipeline process has begun. I will notify you when done."
                    "failed" -> "Sorry! Your pipeline process failed!"
                    "pending" -> "Your pipeline process added into Pending queue. Please wait..."
                    else -> ""
                }
                if (messageString.isNotEmpty()) service.notify(roomId, TextMessage(messageString))
                return Response.ok(this)
            }
        }
        return Response.ok<Any>()
    }

    @PostMapping("/gitlab/merge_request/callback")
    fun mergeRequest(
            @RequestHeader(value = "X-Gitlab-Event") type: String,
            @RequestBody event: PullRequest
    ): ResponseEntity<*> {
        if (type == "Merge Request Hook") {
            log.info(event.toJson())
            val url = event.project.gitHttpUrl
            val project = repository.findProjectByUrl(url)
            project?.apply {
                val message = PullRequestMessage(
                        username = event.objectAttributes.lastCommit.author.name ?: "",
                        url = event.objectAttributes.url,
                        mergeStatus = event.objectAttributes.mergeStatus,
                        mergeError = event.objectAttributes.mergeError,
                        branch = event.objectAttributes.sourceBranch,
                        state = event.objectAttributes.state,
                        targetBranch = event.objectAttributes.targetBranch,
                        message = event.objectAttributes.lastCommit.message
                )
                val messageString = message.toString()
                if (messageString.isNotEmpty()) {
                    service.notify(roomId, TextMessage(message.toString()))
                }
                return Response.ok(this)
            }
            return Response.ok(event)
        }
        return Response.ok<Any>()
    }
}