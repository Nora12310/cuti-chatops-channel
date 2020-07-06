package app

import datas.model.gitlab.pineline.PipelineEvent
import com.linecorp.bot.model.message.TextMessage
import datas.model.BuildMessage
import datas.model.Response
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

    @PostMapping("/gitlab/callback")
    fun push(
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
                if (status == "success") {
                    service.notify(roomId, TextMessage(message.toString()))
                }
            }
        }
        return Response.ok<Any>()
    }
}