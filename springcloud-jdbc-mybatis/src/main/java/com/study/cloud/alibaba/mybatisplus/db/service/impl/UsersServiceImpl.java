package com.study.cloud.alibaba.mybatisplus.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.cloud.alibaba.mybatisplus.db.mapper.auto.UsersMapper;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
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
 * @since 2022-12-22
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {


  private IUsersService usersService;

  @Autowired
  public void setUserService( IUsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  public List<Users> findAllUser() {
    return usersService.findAllUser();
  }

  @Override
  public Users getUserAccount(Long userId) {
    return this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getId, userId));
  }

}
