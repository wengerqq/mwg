package com.ruoyi.currency.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.currency.mapper.CurrencyMapper;
import com.ruoyi.currency.domain.Currency;
import com.ruoyi.currency.service.ICurrencyService;

/**
 * 币种管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class CurrencyServiceImpl implements ICurrencyService 
{
    @Autowired
    private CurrencyMapper currencyMapper;

    /**
     * 查询币种管理
     * 
     * @param id 币种管理主键
     * @return 币种管理
     */
    @Override
    public Currency selectCurrencyById(Long id)
    {
        return currencyMapper.selectCurrencyById(id);
    }

    /**
     * 查询币种管理列表
     * 
     * @param currency 币种管理
     * @return 币种管理
     */
    @Override
    public List<Currency> selectCurrencyList(Currency currency)
    {
        return currencyMapper.selectCurrencyList(currency);
    }

    /**
     * 新增币种管理
     * 
     * @param currency 币种管理
     * @return 结果
     */
    @Override
    public int insertCurrency(Currency currency)
    {
        currency.setCreateTime(DateUtils.getNowDate());
        currency.setOperatorId(SecurityUtils.getUserId());
        return currencyMapper.insertCurrency(currency);
    }

    /**
     * 修改币种管理
     * 
     * @param currency 币种管理
     * @return 结果
     */
    @Override
    public int updateCurrency(Currency currency)
    {
        currency.setUpdateTime(DateUtils.getNowDate());
        currency.setOperatorId(SecurityUtils.getUserId());
        return currencyMapper.updateCurrency(currency);
    }

    /**
     * 批量删除币种管理
     * 
     * @param ids 需要删除的币种管理主键
     * @return 结果
     */
    @Override
    public int deleteCurrencyByIds(Long[] ids)
    {
        return currencyMapper.deleteCurrencyByIds(ids);
    }

    /**
     * 删除币种管理信息
     * 
     * @param id 币种管理主键
     * @return 结果
     */
    @Override
    public int deleteCurrencyById(Long id)
    {
        return currencyMapper.deleteCurrencyById(id);
    }
}
