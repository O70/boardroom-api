package org.thraex.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thraex.test.entity.Table1;
import org.thraex.test.mapper.Table1Mapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author 鬼王
 * @date 2021/07/06 17:11
 */
@Service
public class Table1Service extends ServiceImpl<Table1Mapper, Table1> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = Exception.class)
    public Table1 test1(long sleep, boolean async) {
        Table1 t = new Table1();
        t.setId(1);
        t.setData("T1 is updating the row");
        this.saveOrUpdate(t);
        if (async) {
            logger.info("Begin async mock");
            CompletableFuture.runAsync(() -> this.mock(sleep));
        } else {
            logger.info("Begin sync mock");
            this.mock(sleep);
        }

        return t;
    }

    public void mock(long sleep) {
        try {
            Thread.sleep(sleep);
            logger.info("Mock end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
