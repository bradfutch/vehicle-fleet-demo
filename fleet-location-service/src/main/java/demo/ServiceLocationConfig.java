package demo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceLocationConfig {
    public class AppConfig {

        @Value("${spring.data.mongodb.uri}")
        public String mongoHost;

        public @Bean
        MongoClient mongoClient() {
            return new MongoClient();
        }
    }
}
