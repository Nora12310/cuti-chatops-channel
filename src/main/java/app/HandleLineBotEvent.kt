package app

import com.linecorp.bot.model.event.*
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.springframework.web.bind.annotation.RestController
import repository.ILineEventService


@RestController
@LineMessageHandler
class HandleLineBotEvent(private val mLineService: ILineEventService) {

    @Suppress("unused")
    @EventMapping
    fun handleTextMessageEvent(event: MessageEvent<TextMessageContent>) {
        return mLineService.command(event)
    }

    @EventMapping
    @Suppress("unused")
    fun handleJoinEvent(event: JoinEvent) {
        mLineService.join(event)
    }

    @EventMapping
    @Suppress("unused")
    fun handleLeaveEvent(event: LeaveEvent) {
        mLineService.leave(event)
    }

    @EventMapping
    @Suppress("unused")
    fun handlePostBackEvent(event: PostbackEvent) {
        mLineService.postBack(event)
    }

    @EventMapping
    @Suppress("unused")
    fun handleOtherEvent(event: Event) {
        mLineService.other(event)
    }
}