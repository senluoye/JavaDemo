package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.BrandDao;
import entity.Brand;
import service.BrandService;
import org.springframework.stereotype.Service;

/**
 * (Brand)表服务实现类
 *
 * @author mybatisplus
 * @since 2022-11-29 08:43:35
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, Brand> implements BrandService {

}

