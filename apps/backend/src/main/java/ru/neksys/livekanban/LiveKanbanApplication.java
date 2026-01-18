package ru.neksys.livekanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.neksys.livekanban.config.AppProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class LiveKanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveKanbanApplication.class, args);
    }

}
