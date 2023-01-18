package com.study.cloud.alibaba.mybatisplus.db.controller;


import com.study.cloud.alibaba.mybatisplus.db.model.auto.Product;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import com.study.cloud.alibaba.mybatisplus.db.service.IProductService;
import com.study.cloud.alibaba.mybatisplus.db.service.IUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2023-01-01
 */
@RestController
@RequestMapping("/db/product")
public class ProductController {
  private IProductService productService;

  @Autowired
  public void setUsersService(@Qualifier(value = "productServiceImpl") IProductService productService) {
    this.productService = productService;
  }


  @RequestMapping(path = "/getProduct/{index}", method = RequestMethod.GET)
  public Product getUser(@PathVariable int index){
    return productService.getById(index);
  }


  @RequestMapping(path= "/getAllProducts", method = RequestMethod.GET)
  public List<Product> getAllProducts(){
    return productService.findAllProduct();
  }
}
