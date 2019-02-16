package rashjz.info.app.handler;


import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class EchoHandlerTest {
    private final EchoHandler handler = new EchoHandler();

    @Test
    public void shouldEchoMessage() throws Exception {
        var mockSession = mock(WebSocketSession.class);
        var msg = new TextMessage("Hello World!");
        handler.handleTextMessage(mockSession, msg);
        verify(mockSession, times(1))
                .sendMessage(eq(new TextMessage("RECEIVED: Hello World!")));
    }
}