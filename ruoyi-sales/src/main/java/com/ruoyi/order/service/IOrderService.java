package com.ruoyi.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.order.domain.Order;

/**
 * 订单管理Service接口
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
public interface IOrderService 
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
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 查询订单状态数据
     *
     * @return 返回数据集
     */
    public Map<String,Object> getOrderStatusList();

    /**
     * 更新订单自定义标记
     *
     * @param data json数据
     * @return 结果
     */
    public int updateOrderSign(JSONObject data);

    /**
     * 更新客服备注
     *
     * @param data json数据
     * @return 结果
     */
    public int updateOrderRemarks(JSONObject data);

    /**
     * 提交发货审核
     *
     * @param order 订单管理
     * @return 结果
     */
    public int updateOrderDa(Order order);
}
