package org.thraex.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 鬼王
 * @date 2021/07/06 16:54
 */
public class Controller<S extends IService<?>> {

    @Autowired
    protected S service;

}
