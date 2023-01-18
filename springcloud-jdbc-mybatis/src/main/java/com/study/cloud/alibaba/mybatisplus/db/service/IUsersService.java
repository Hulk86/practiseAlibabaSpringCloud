package com.study.cloud.alibaba.mybatisplus.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2022-12-22
 */

public interface IUsersService extends IService<Users> {
  public List<Users> findAllUser();
  /**
   * 获取用户账户信息
   */
 public Users getUserAccount(Long userId);
}
