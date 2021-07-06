package org.thraex.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thraex.test.entity.Table3;
import org.thraex.test.mapper.Table3Mapper;

/**
 * @author 鬼王
 * @date 2021/07/06 17:11
 */
@Service
public class Table3Service extends ServiceImpl<Table3Mapper, Table3> {

    @Transactional(rollbackFor = Exception.class)
    public Table3 test1() {
        Table3 t = new Table3();
        t.setId(1);
        t.setData("T3 is updating the row");
        this.saveOrUpdate(t);

        return t;
    }

}
