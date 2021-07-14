package org.thraex.ws;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 鬼王
 * @date 2021/07/07 16:06
 */
//@Component
public class BookStompSessionHandler extends StompSessionHandlerAdapter {

    //private SockJsClient sockJsClient;
    //
    //private WebSocketStompClient stompClient;
    //
    //private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    //
    //public BookStompSessionHandler() {
    //    List<Transport> transports = new ArrayList<>();
    //    transports.add(new WebSocketTransport(new StandardWebSocketClient()));
    //
    //    this.sockJsClient = new SockJsClient(transports);
    //    this.stompClient = new WebSocketStompClient(sockJsClient);
    //    this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    //
    //    //this.stompClient.connect("/websocket", this.headers,)
    //}

    private final AtomicReference<Throwable> failure;

    public BookStompSessionHandler(AtomicReference<Throwable> failure) {
        this.failure = failure;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        this.failure.set(new Exception(headers.toString()));
    }

    @Override
    public void handleException(StompSession s, StompCommand c, StompHeaders h, byte[] p, Throwable ex) {
        this.failure.set(ex);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable ex) {
        this.failure.set(ex);
    }
}
