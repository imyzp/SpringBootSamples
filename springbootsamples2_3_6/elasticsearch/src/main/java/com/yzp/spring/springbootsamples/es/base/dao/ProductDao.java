package com.yzp.spring.springbootsamples.es.base.dao;

import com.yzp.spring.springbootsamples.es.base.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao  extends ElasticsearchRepository<Product,Long> {
}
