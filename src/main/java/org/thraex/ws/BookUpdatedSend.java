package org.thraex.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 鬼王
 * @date 2021/07/07 16:35
 */
@Component
public class BookUpdatedSend {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SockJsClient sockJsClient;

    private WebSocketStompClient stompClient;

    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    public BookUpdatedSend() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        this.sockJsClient = new SockJsClient(transports);
        this.stompClient = new WebSocketStompClient(sockJsClient);
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        //this.stompClient.connect("ws://localhost:8037/websocket", this.headers, new StompSessionHandler());
        //this.stompClient
    }

    public void run() {
        this.stompClient.connect("ws://localhost:8037/websocket", this.headers, new StompSessionHandler());

        //final CountDownLatch latch = new CountDownLatch(1);
        //final AtomicReference<Throwable> failure = new AtomicReference<>();

        //StompSessionHandler handler = new StompSessionHandler(failure) {
        //    @Override
        //    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        //        //session.subscribe("/topic/book/updated", new StompFrameHandler() {
        //        //
        //        //    @Override
        //        //    public Type getPayloadType(StompHeaders headers) {
        //        //        return Map.class;
        //        //    }
        //        //
        //        //    @Override
        //        //    public void handleFrame(StompHeaders headers, Object payload) {
        //        //        logger.info("handleFrame: {}", payload);
        //        //    }
        //        //});
        //    }
        //};
    }

    private class StompSessionHandler extends StompSessionHandlerAdapter {

        private final AtomicReference<Throwable> failure = new AtomicReference<>();

        //public StompSessionHandler(AtomicReference<Throwable> failure) {
        //    this.failure = failure;
        //}

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

}
