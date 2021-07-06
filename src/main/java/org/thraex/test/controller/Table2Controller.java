package org.thraex.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.BaseController;
import org.thraex.test.entity.Table2;
import org.thraex.test.service.Table2Service;

/**
 * @author 鬼王
 * @date 2021/07/06 16:45
 */
@RestController
@RequestMapping("table2")
public class Table2Controller extends BaseController<Table2, Table2Service> {

    @GetMapping("test1")
    public ResponseEntity<Table2> test1() {
        return ResponseEntity.ok(service.test1());
    }

}
