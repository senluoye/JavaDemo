package org.spring.springboot.dao;

import org.spring.springboot.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CityRepository
 * @Description CityRepository 接口只要继承 ReactiveMongoRepository 类即可。
 * 默认会提供很多实现，比如 CRUD 和列表查询参数相关的实现
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-09 15:47
 */
@Repository
public interface CityRepository extends ReactiveMongoRepository<City, Long> {
}

