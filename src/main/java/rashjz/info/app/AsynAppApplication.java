package rashjz.info.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
//@EnableWebSocketMessageBroker
public class AsynAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsynAppApplication.class, args);
    }

}

