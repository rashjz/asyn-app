package rashjz.info.app.handler;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
public class EchoHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        var msg = message.getPayload();
        log.info("message is {} ", msg);
        session.sendMessage(new TextMessage("RECEIVED: " + msg));
    }

}
