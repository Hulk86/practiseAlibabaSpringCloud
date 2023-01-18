package com.study.cloud.alibaba.mybatisplus.db.model.auto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoderhulk...
 * @since 2022-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Users extends Model {

    private static final long serialVersionUID = 1L;

    private String serialNum;

    private String address;

    private String bindAccountId;

    private String email;

    private String mobile;

    private String name;

    private LocalDateTime createTime;

    private Integer id;


}
