package di

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.spring.boot.LineBotProperties
import datas.dao.ProjectDao
import datas.dao.RoomDao
import datas.network.LineApiGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import repository.ILineEventService
import repository.LineEventServiceImpl
import repository.LineRepository

@Configuration
class LineBotAppModule {

    @Bean
    @Autowired
    fun createLineBotServiceV2(
            client: LineMessagingClient,
            repository: LineRepository): ILineEventService {
        return LineEventServiceImpl(client, repository)
    }

    @Bean
    @Autowired
    fun createLineRepository(
            properties: LineBotProperties,
            roomDao: RoomDao,
            projectDao: ProjectDao,
            api: LineApiGenerator.APILink
    ): LineRepository {
        return LineRepository(properties, roomDao, projectDao, api)
    }
}