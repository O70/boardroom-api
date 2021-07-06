package org.thraex.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.BaseController;
import org.thraex.test.entity.Table3;
import org.thraex.test.service.Table3Service;

/**
 * @author 鬼王
 * @date 2021/07/06 16:45
 */
@RestController
@RequestMapping("table3")
public class Table3Controller extends BaseController<Table3, Table3Service> {

    @GetMapping("test1")
    public ResponseEntity<Table3> test1() {
        return ResponseEntity.ok(service.test1());
    }

}
