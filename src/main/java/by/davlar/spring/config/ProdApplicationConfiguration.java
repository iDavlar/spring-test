package by.davlar.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("prod")
@Configuration
@PropertySource("classpath:prod.properties")
public class ProdApplicationConfiguration {
}
