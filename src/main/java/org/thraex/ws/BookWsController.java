package org.thraex.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.Map;

/**
 * @author 鬼王
 * @date 2021/07/07 15:11
 */
@Controller
public class BookWsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @MessageMapping("/book/updated")
    @SendTo("/topic/book/updated")
    public ResponseEntity<Map<String, Object>> updated(String id) {
        logger.info("The book is updated: {}", id);
        return ResponseEntity.ok(Collections.EMPTY_MAP);
    }

}
