package io.github.samirsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// DEVELOPMENT
//@SpringBootApplication
//@EnableJpaAuditing
//public class Main {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//    }
//}


// TOMCAT PRODUCTION
@SpringBootApplication
@EnableJpaAuditing
public class Main extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}