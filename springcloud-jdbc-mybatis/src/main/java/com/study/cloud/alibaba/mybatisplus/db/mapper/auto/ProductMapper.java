package com.study.cloud.alibaba.mybatisplus.db.mapper.auto;

import com.study.cloud.alibaba.mybatisplus.db.model.auto.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2023-01-01
 */
public interface ProductMapper extends BaseMapper<Product> {

  public List<Product> findAll();
}
