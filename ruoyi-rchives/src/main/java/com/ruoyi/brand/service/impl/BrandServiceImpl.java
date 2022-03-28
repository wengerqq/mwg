package com.ruoyi.brand.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.brand.mapper.BrandMapper;
import com.ruoyi.brand.domain.Brand;
import com.ruoyi.brand.service.IBrandService;

/**
 * 品牌管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class BrandServiceImpl implements IBrandService 
{
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    @Override
    public Brand selectBrandById(Long id)
    {
        return brandMapper.selectBrandById(id);
    }

    /**
     * 查询品牌管理列表
     * 
     * @param brand 品牌管理
     * @return 品牌管理
     */
    @Override
    public List<Brand> selectBrandList(Brand brand)
    {
        return brandMapper.selectBrandList(brand);
    }

    /**
     * 新增品牌管理
     * 
     * @param brand 品牌管理
     * @return 结果
     */
    @Override
    public int insertBrand(Brand brand)
    {
        brand.setCreateTime(DateUtils.getNowDate());
        brand.setOperatorId(SecurityUtils.getUserId());
        return brandMapper.insertBrand(brand);
    }

    /**
     * 修改品牌管理
     * 
     * @param brand 品牌管理
     * @return 结果
     */
    @Override
    public int updateBrand(Brand brand)
    {
        brand.setUpdateTime(DateUtils.getNowDate());
        brand.setOperatorId(SecurityUtils.getUserId());
        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteBrandByIds(Long[] ids)
    {
        return brandMapper.deleteBrandByIds(ids);
    }

    /**
     * 删除品牌管理信息
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteBrandById(Long id)
    {
        return brandMapper.deleteBrandById(id);
    }
}
