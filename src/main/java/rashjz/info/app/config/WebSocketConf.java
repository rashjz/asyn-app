package rashjz.info.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import rashjz.info.app.handler.EchoHandler;

@Configuration
public class WebSocketConf implements WebSocketConfigurer {

    @Bean
    public EchoHandler echoHandler() {
        return new EchoHandler();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler(), "/echo");
    }


}
