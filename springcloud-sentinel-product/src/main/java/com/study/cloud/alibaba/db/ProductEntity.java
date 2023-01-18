package com.study.cloud.alibaba.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ：Hulk
 * @date ：Created in 2022/1/25 19:36
 * @description ：TODO...
 * @modified By ：
 * @version:
 */

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @Column(columnDefinition = "bigint", updatable = false, unique = true)
    private Long id;

    @Column(columnDefinition = "varchar(64) Comment '货品类型编号，对应productInfo 的serial_id'")
    private String templateId;

    @Column(columnDefinition = "double(10,2) Comment '数量'")
    private Float amount;

    @Column(columnDefinition = "varchar(32) Comment '放置的托盘，如果托盘被解绑，商品的bindTray也会改成解绑后的临时位置'")
    private String bindTrayId;

    @Column(columnDefinition = "varchar(64) Comment '比如锁定了状态，表示物品被出货选定'", nullable=false)
    private String state;

    @Column(length = 128)
    private String description;

    @UpdateTimestamp
    @Column()
    @JsonIgnore
    private Date lastUpdateTime;

    @CreationTimestamp
    @Column()
    private Date createTimestamp;


    public ProductEntity(ProductEntity cpEntity){
        this.templateId = cpEntity.templateId;
        this.amount = cpEntity.amount;
        this.bindTrayId = cpEntity.bindTrayId;
        this.state = cpEntity.state;
        this.description = cpEntity.description;
        this.createTimestamp = cpEntity.createTimestamp;
        this.lastUpdateTime = cpEntity.lastUpdateTime;



    }

}
