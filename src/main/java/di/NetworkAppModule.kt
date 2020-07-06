package di

import datas.network.LineApiGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NetworkAppModule {

    @Bean
    fun createLineGenerator(): LineApiGenerator {
        return LineApiGenerator()
    }

    @Bean
    @Autowired
    fun createLineService(
            generator: LineApiGenerator
    ): LineApiGenerator.APILink {
        return generator.createService(LineApiGenerator.APILink::class.java)
    }
}