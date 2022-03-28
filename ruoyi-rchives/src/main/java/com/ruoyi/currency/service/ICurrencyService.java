package com.ruoyi.currency.service;

import java.util.List;
import com.ruoyi.currency.domain.Currency;

/**
 * 币种管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface ICurrencyService 
{
    /**
     * 查询币种管理
     * 
     * @param id 币种管理主键
     * @return 币种管理
     */
    public Currency selectCurrencyById(Long id);

    /**
     * 查询币种管理列表
     * 
     * @param currency 币种管理
     * @return 币种管理集合
     */
    public List<Currency> selectCurrencyList(Currency currency);

    /**
     * 新增币种管理
     * 
     * @param currency 币种管理
     * @return 结果
     */
    public int insertCurrency(Currency currency);

    /**
     * 修改币种管理
     * 
     * @param currency 币种管理
     * @return 结果
     */
    public int updateCurrency(Currency currency);

    /**
     * 批量删除币种管理
     * 
     * @param ids 需要删除的币种管理主键集合
     * @return 结果
     */
    public int deleteCurrencyByIds(Long[] ids);

    /**
     * 删除币种管理信息
     * 
     * @param id 币种管理主键
     * @return 结果
     */
    public int deleteCurrencyById(Long id);
}
