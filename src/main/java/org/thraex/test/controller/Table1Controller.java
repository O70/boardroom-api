package org.thraex.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.BaseController;
import org.thraex.test.entity.Table1;
import org.thraex.test.service.Table1Service;
import org.thraex.test.service.Table2Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author 鬼王
 * @date 2021/07/06 16:45
 */
@RestController
@RequestMapping("table1")
public class Table1Controller extends BaseController<Table1, Table1Service> {

    @GetMapping("test1")
    public ResponseEntity<Table1> test1(long sleep, boolean async) {
        return ResponseEntity.ok(service.test1(sleep, async));
    }

    @Autowired
    private Table2Service table2Service;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("test2")
    public ResponseEntity<String> test2(long sleep, boolean async) {
        CompletableFuture.runAsync(() -> {
            logger.info("Begin table1 service");
            service.test1(sleep, async);
            logger.info("End table1 service");
        });
        CompletableFuture.runAsync(() -> {
            logger.info("Begin table2 service");
            table2Service.test1();
            logger.info("End table2 service");
        });

        return ResponseEntity.ok("Success");
    }

}
