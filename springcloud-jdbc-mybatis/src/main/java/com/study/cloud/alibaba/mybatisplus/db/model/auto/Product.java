package com.study.cloud.alibaba.mybatisplus.db.model.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-01-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Product extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数量
     */
    private Double amount;

    /**
     * 放置的托盘，如果托盘被解绑，商品的bindTray也会改成解绑后的临时位置
     */
    private String bindTrayId;

    private LocalDateTime createTimestamp;

    private String description;

    private LocalDateTime lastUpdateTime;

    /**
     * 比如锁定了状态，表示物品被出货选定
     */
    private String state;

    /**
     * 货品类型编号，对应productInfo 的serial_id
     */
    private String templateId;


}
