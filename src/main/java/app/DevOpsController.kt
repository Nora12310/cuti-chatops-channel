package app

import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import line.LineBotService
import org.springframework.web.bind.annotation.RestController


@RestController
@LineMessageHandler
class DevOpsController {
    private lateinit var mLineBotService: LineBotService

    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>) {
        mLineBotService.handleTextMessage(event)
    }

}