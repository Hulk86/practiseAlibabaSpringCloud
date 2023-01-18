package com.study.cloud.alibaba.mybatisplus.db.controller;


import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import com.study.cloud.alibaba.mybatisplus.db.service.IUsersService;
import io.swagger.annotations.Api;
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
 * @since 2022-12-22
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("/db/users")
public class UsersController {

  private IUsersService usersService;

  @Autowired
  public void setUsersService(@Qualifier(value = "usersServiceImpl") IUsersService usersService) {
    this.usersService = usersService;
  }


  @RequestMapping(path = "/getUser/{theIndex}", method = RequestMethod.GET)
  //@PostMapping("/getUser/{theIndex}")
  public Users getUser(@PathVariable Integer theIndex){
    return usersService.getById(theIndex);
  }


  @RequestMapping(path="/findAllUser",  method = RequestMethod.GET)
  public List<Users> findAllUser(){
    return usersService.findAllUser();
  }
}
