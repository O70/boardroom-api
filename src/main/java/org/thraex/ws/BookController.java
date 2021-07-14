package org.thraex.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鬼王
 * @date 2021/07/07 15:08
 */
@RestController
@RequestMapping("books")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookUpdatedSend bookUpdatedSend;

    @PostMapping("approve")
    public ResponseEntity<Boolean> approve(Integer index) {
        bookUpdatedSend.run();
        logger.info("Approve book: {}", index);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(Integer index) {
        logger.info("Update book: {}", index);
        return ResponseEntity.ok(true);
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity<Boolean> cancel(@PathVariable("id") Integer index) {
        logger.info("Cancel book: {}", index);
        return ResponseEntity.ok(true);
    }

}
