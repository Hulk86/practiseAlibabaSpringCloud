package com.study.cloud.alibaba.mybatisplus.db.service.impl;

import com.study.cloud.alibaba.mybatisplus.db.model.auto.Product;
import com.study.cloud.alibaba.mybatisplus.db.mapper.auto.ProductMapper;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import com.study.cloud.alibaba.mybatisplus.db.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.cloud.alibaba.mybatisplus.db.service.IUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2023-01-01
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

  private IProductService productService;


  @Autowired
  public void setProductService( IProductService productService) {
    this.productService = productService;
  }

  @Override
  public List<Product> findAllProduct() {
    return productService.findAllProduct();
  }

}
