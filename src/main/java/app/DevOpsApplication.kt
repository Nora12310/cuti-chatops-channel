package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class DevOpsApplication

fun main(args: Array<String>) {
    SpringApplication.run(DevOpsApplication::class.java, *args)
}
