package line

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.model.message.Message
import com.linecorp.bot.model.message.TextMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException


@Service
class LineBotService @Autowired constructor(private val client: LineMessagingClient) {
    companion object {
        private const val UNKNOWN_COMMAND = "Sorry, I don't understand your command. Please contact my boss for more information."
        private const val BUILD_COMMAND = "/build"


        private const val BOT_INTRODUCE = "Hi team, \n This is **Cuti** bot, created by Android team. i will build your apk file now."
    }

    fun handleTextMessage(event: MessageEvent<TextMessageContent>) {
        val token = event.replyToken
        val text = event.message.text
        if (text.startsWith('/')) {
            val command = text.split(" ")
            when (command[0]) {
                BUILD_COMMAND -> {
                    replyText(token, BOT_INTRODUCE)
                }
                else -> {
                    replyText(token, UNKNOWN_COMMAND)
                }
            }
        }
    }

    private fun replyText(token: String, message: String) {
        if (token.isEmpty()) return
        var newMessage = ""
        if (message.length > 1000) {
            newMessage = "${message.substring(0, 1000 - 2)}..."
        }
        this.reply(token, TextMessage(newMessage))
    }

    private fun reply(token: String,
                      vararg messages: Message,
                      notificationDisabled: Boolean = false) {
        try {
            client.replyMessage(ReplyMessage(token, listOf(*messages), notificationDisabled)).get()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        }
    }
}