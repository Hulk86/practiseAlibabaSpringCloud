package com.study.cloud.alibaba.mybatisplus.db.service;

import com.study.cloud.alibaba.mybatisplus.db.model.auto.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2023-01-01
 */
public interface IProductService extends IService<Product> {

  public List<Product> findAllProduct();
}
