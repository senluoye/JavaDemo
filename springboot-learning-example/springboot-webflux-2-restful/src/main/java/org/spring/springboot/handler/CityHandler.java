package org.spring.springboot.handler;

import org.spring.springboot.dao.CityRepository;
import org.spring.springboot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @ClassName CityHandler
 * @Description 处理类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-09 13:24
 */
@Component
public class CityHandler {

    private final CityRepository cityRepository;

    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Mono<Long> save(City city) {
        // create 从 MonoSink 中创建一个 Mono
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.save(city)));
    }

    public Mono<City> findCityById(Long id) {
        // 从 Optional 对象或者 null 来创建一个 Mono
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityRepository.findAll());
    }

    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.updateCity(city)));
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.deleteCity(id)));
    }
}
