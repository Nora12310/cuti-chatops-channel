package app

import di.LineBotAppModule
import di.MongoAppModule
import di.NetworkAppModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import


@SpringBootApplication
@Import(
        MongoAppModule::class,
        LineBotAppModule::class,
        NetworkAppModule::class
)
class DevOpsApplication

fun main(args: Array<String>) {
    SpringApplication.run(DevOpsApplication::class.java, *args)
}