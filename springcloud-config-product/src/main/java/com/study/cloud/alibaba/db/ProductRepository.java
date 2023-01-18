package com.study.cloud.alibaba.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ：Hulk
 * @date ：Created in 2022/3/5 18:19
 * @description ：TODO...
 * @modified By ：
 * @version:
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>,
    JpaSpecificationExecutor<ProductEntity> {

  public Page<ProductEntity> findAll(Pageable pageable);
  public List<ProductEntity> findAll(Sort t);
  @Override
  public Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);


  public Optional<ProductEntity> findByTemplateIdAndBindTrayId(String productTypeId, String bindTrayId);


}
