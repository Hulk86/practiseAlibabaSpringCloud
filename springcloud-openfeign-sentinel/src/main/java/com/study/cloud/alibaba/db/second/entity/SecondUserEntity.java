package com.study.cloud.alibaba.db.second.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author ：Hulk
 * @date ：Created in 2021/10/22 20:39
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@Table(name = "users")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SecondUserEntity {

    //Provided id of the wrong type for class com.ganfeng.mgmtCoreServer.service.account.models.UserEntity. Expected: class java.lang.Integer, got class java.lang.String
    @Id
    @Column(columnDefinition = "int(11) not null", updatable = false, unique = true)
    private int id;

    @Column(name="serial_num", length = 32, updatable = false, unique = true)
    private String serialNum;

    @Column(length = 32)
    private String name;

    @Column(length = 48)
    private String mobile;

    @Column(length = 48)
    private String email;

    @Column(length = 200)
    private String address;

    @Column(name = "bind_account_id", length = 200)
    private String bindAccountId;

    @CreationTimestamp
    @Column(name= "create_time")
    private Date createTime;

}
