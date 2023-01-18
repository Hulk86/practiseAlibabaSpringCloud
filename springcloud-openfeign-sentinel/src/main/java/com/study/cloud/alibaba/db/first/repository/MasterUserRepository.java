package com.study.cloud.alibaba.db.first.repository;

import com.study.cloud.alibaba.db.first.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：Hulk
 * @date ：Created in 2021/10/23 14:50
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@Repository
public interface MasterUserRepository extends JpaRepository<UserEntity,String>{

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findBySerialNum(String id);

    Optional<UserEntity> findByBindAccountId(String bindAccount);



    @Modifying
    @Transactional
    @Query(value = "update users T set T.bind_account_id=:account_name where T.serial_num=:serialNum", nativeQuery = true)
    void updateBindAccountInfo(@Param("serialNum") String serialNum, @Param("account_name") String account_name);

   // @Modifying
    //@Transactional
    //@Query(value = "delete from users T where id=:userId", nativeQuery = true)
    void deleteBySerialNum(String userId);

    //select a.bind_account_id as account,a.`name` from users as a where a.bind_account_id in (select b.account from account as b where b.role like  '%keeper%' and b.status='normal');
    @Query(value = "select * from users as a where a.bind_account_id in (select b.account from account as b where b.role like  %:userRole% and b.status='normal')"
            , nativeQuery = true)
    List<UserEntity>  findAllSimpleUserListByRole(@Param("userRole") String userRole);


  @Query(value = "SELECT * FROM users WHERE bind_account_id is null or LENGTH(bind_account_id) =0 order by id desc;"
      , nativeQuery = true)
  List<UserEntity>  findAllUnbindUserList();

}