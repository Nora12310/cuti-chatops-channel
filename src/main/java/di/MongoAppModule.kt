package di

import datas.dao.ProjectDao
import datas.dao.RoomDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import repository.ProjectRepository
import repository.RoomRepository


@Configuration
@EnableMongoRepositories(basePackageClasses = [
    RoomRepository::class,
    ProjectRepository::class
])
class MongoAppModule {

    @Bean
    @Autowired
    fun createRoomDao(repository: RoomRepository): RoomDao {
        return RoomDao(repository)
    }

    @Bean
    @Autowired
    fun createProjectDao(repository: ProjectRepository): ProjectDao {
        return ProjectDao(repository)
    }
}