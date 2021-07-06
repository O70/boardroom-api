package org.thraex.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thraex.test.entity.Table1;
import org.thraex.test.entity.Table2;
import org.thraex.test.entity.Table3;
import org.thraex.test.mapper.Table2Mapper;

/**
 * @author 鬼王
 * @date 2021/07/06 17:11
 */
@Service
public class Table2Service extends ServiceImpl<Table2Mapper, Table2> {

    @Autowired
    private Table1Service table1Service;

    @Autowired
    private Table3Service table3Service;

    @Transactional(rollbackFor = Exception.class)
    public Table2 test1() {
        Table2 t = new Table2();
        t.setId(1);
        t.setData("T2 is updating the row");
        this.saveOrUpdate(t);

        Table3 t3 = new Table3();
        t3.setId(1);
        t3.setData("T2 is updating the row");
        table3Service.saveOrUpdate(t3);

        Table1 t1 = new Table1();
        t1.setId(1);
        t1.setData("T2 is updating the row");
        table1Service.saveOrUpdate(t1);

        return t;
    }

}
