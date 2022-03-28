package com.ruoyi.brand.mapper;

import java.util.List;
import com.ruoyi.brand.domain.Brand;

/**
 * 品牌管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface BrandMapper 
{
    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    public Brand selectBrandById(Long id);

    /**
     * 查询品牌管理列表
     * 
     * @param brand 品牌管理
     * @return 品牌管理集合
     */
    public List<Brand> selectBrandList(Brand brand);

    /**
     * 新增品牌管理
     * 
     * @param brand 品牌管理
     * @return 结果
     */
    public int insertBrand(Brand brand);

    /**
     * 修改品牌管理
     * 
     * @param brand 品牌管理
     * @return 结果
     */
    public int updateBrand(Brand brand);

    /**
     * 删除品牌管理
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    public int deleteBrandById(Long id);

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrandByIds(Long[] ids);
}
