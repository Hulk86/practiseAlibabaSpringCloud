package com.study.cloud.alibaba.mybatisplus.db.mapper.auto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.cloud.alibaba.mybatisplus.db.model.auto.Users;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2022-12-22
 */
public interface UsersMapper extends BaseMapper<Users> {
  public List<Users> findAllUser();
}
