package entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Brand)表实体类
 *
 * @author mybatisplus
 * @since 2022-11-29 08:43:32
 */
@SuppressWarnings("serial")
public class Brand extends Model<Brand> {
    
    private Integer id;
    
    private String describe;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

