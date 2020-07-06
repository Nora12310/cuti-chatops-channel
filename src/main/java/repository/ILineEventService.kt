package repository

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.PushMessage
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.event.*
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.message.Message
import com.linecorp.bot.model.message.TextMessage
import common.Resource
import common.Resource.ADD_URL_MESSAGE
import common.Resource.INVALID_URL_MESSAGE
import datas.entity.ProjectEntity
import datas.model.Project
import ext.toJson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URL


interface ILineEventService {
    fun reply(token: String, message: Message)
    fun notify(targetId: String, message: Message)
    fun join(event: JoinEvent)
    fun leave(event: LeaveEvent)
    fun postBack(event: PostbackEvent)
    fun other(event: Event)
    fun command(event: MessageEvent<TextMessageContent>)
}


class LineEventServiceImpl(
        private val client: LineMessagingClient,
        private val repository: LineRepository
) : ILineEventService {
    private val log: Logger = LoggerFactory.getLogger(ILineEventService::class.java)
    override fun reply(token: String, message: Message) {
        log.info("reply_message=${message.toJson()}")
        client.replyMessage(ReplyMessage(token, message))
    }

    override fun notify(targetId: String, message: Message) {
        log.info("reply_message=${message.toJson()}")
        client.pushMessage(PushMessage(targetId, message)).get()
    }

    override fun join(event: JoinEvent) {
        GlobalScope.launch {
            repository.join(event.source.senderId)
        }
    }

    override fun leave(event: LeaveEvent) {
        GlobalScope.launch {
            repository.leave(event.source.senderId)
        }
    }

    override fun postBack(event: PostbackEvent) {
        //return post back data to group chat.
        reply(event.replyToken, TextMessage(event.postbackContent.data))
    }

    override fun other(event: Event) {
        log.info("Received message(Ignored): ${event.toJson()}")
    }

    override fun command(event: MessageEvent<TextMessageContent>) {
        val text = event.message.text
        if (text.startsWith("/")) {
            val commands = text.split(" ")
            if (commands.isNotEmpty()) {
                when (commands[0]) {
                    Resource.INFO -> handleInfoEvent(commands.getOrNull(1), event)
                    Resource.BUILD -> handleBuildApk(commands.getOrNull(1), event)
                    Resource.REGISTER -> handleRegisterGitRepo(commands.getOrNull(1), event)
                    Resource.UNREGISTER -> handleUnregisterGitRepo(commands.getOrNull(1), event)
                }
            }
        }
    }

    private fun handleRegisterGitRepo(command: String?, event: MessageEvent<TextMessageContent>) {
        val url = command ?: return reply(event.replyToken, TextMessage(INVALID_URL_MESSAGE))
        if (isValidURL(url)) {
            GlobalScope.launch {
                repository.register(ProjectEntity(
                        id = Project.generateId(command),
                        roomId = event.source.senderId,
                        gitUrl = url,
                        name = "",
                        key = ""
                ))
                reply(event.replyToken, TextMessage(ADD_URL_MESSAGE))
            }
        } else {
            reply(event.replyToken, TextMessage(INVALID_URL_MESSAGE))
        }
    }

    private fun handleUnregisterGitRepo(command: String?, event: MessageEvent<TextMessageContent>) {
        val url = command ?: return reply(event.replyToken, TextMessage(INVALID_URL_MESSAGE))
        if (isValidURL(url)) {
            GlobalScope.launch { repository.unregister(url) }
        } else {
            reply(event.replyToken, TextMessage(INVALID_URL_MESSAGE))
        }
    }

    private fun handleInfoEvent(command: String?, event: MessageEvent<TextMessageContent>) {
        val message = when (command) {
            Resource.SENDER_ID -> TextMessage(event.source.senderId)
            else -> TextMessage(event.toJson())
        }
        reply(event.replyToken, message)
    }

    private fun handleBuildApk(command: String?, event: MessageEvent<TextMessageContent>) {
        val project: ProjectEntity? = repository.findProjectByKey(command)
        val message = when {
            project != null -> {
                //we will start call ci/cd from here to build apk file.
                TextMessage(Resource.BUILD_MESSAGE)
            }
            else -> TextMessage(Resource.BUILD_MESSAGE)
        }
        reply(event.replyToken, message)
    }

    private fun isValidURL(urlString: String?): Boolean {
        return try {
            val url = URL(urlString)
            url.toURI()
            true
        } catch (exception: Exception) {
            false
        }
    }
}