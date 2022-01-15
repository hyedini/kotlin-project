package kt.project.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@EnableTransactionManagement
@Configuration
class DatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun hikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    fun dataSource(): DataSource {
        return HikariDataSource(hikariConfig())
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }
}