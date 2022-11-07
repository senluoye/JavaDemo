package com.atguigu.distributed.lock.mapper;

import com.atguigu.distributed.lock.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName StockMapper
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-15 10:20
 */
public interface StockMapper extends BaseMapper<Stock> {

    @Update("update db_stock set count = count - #{quality} where product_code = #{productCode} and count >= #{quality}")
    int update(@Param("productCode") String productCode, @Param("quality") Integer quality);

    @Select("select * from db_stock where product_code = #{productCode} for update")
    Stock getCountByFOrUpdate(String productCode);

}
