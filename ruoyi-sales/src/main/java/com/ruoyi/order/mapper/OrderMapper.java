package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.Order;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
public interface OrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    public Order selectOrderById(Long id);

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);

    /**
     * 修改订单管理某字段
     *
     * @param order 订单管理
     * @return 结果
     */
    public int updateOrderValue(Order order);

    /**
     * 根据ID集合查询订单管理列表
     *
     * @param order 订单管理
     * @return 订单管理集合
     */
    public List<Order> selectOrderByIds(Order order);
}
