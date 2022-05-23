package com.ruoyi.order.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.detail.domain.OrderDetail;
import com.ruoyi.detail.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderMapper;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public Order selectOrderById(Long id)
    {
        Order order = orderMapper.selectOrderById(id);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderKeyId(order.getId());
        List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailList(orderDetail);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("orderDetailList",orderDetailList);
        order.setParams(mapData);
        return order;
    }

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        List<Order> orderList = orderMapper.selectOrderList(order);
        for(Order order1:orderList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderKeyId(order1.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailList(orderDetail);
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("orderDetailList",orderDetailList);
            order1.setParams(mapData);
        }
        return orderList;
    }

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids)
    {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }

    /**
     * 查询订单状态数据
     *
     * @return 返回数据集
     */
    public Map<String,Object> getOrderStatusList(){

        Map<String,Object> bigMap = new HashMap<>();
        Map<String,Object> mapData1 = new HashMap<>();
        Map<String,Object> mapData2 = new HashMap<>();
        Map<String,Object> mapData3 = new HashMap<>();

        List<SysDictData> orderStatusData = DictUtils.getDictCache("sys_order_status");
        for (SysDictData sdd:orderStatusData){
            switch (sdd.getDictValue()){
                case "1":
                    List<SysDictData> psrList = DictUtils.getDictCache("order_pending_shipment_review");
                    mapData1.put("1",psrList);

                    List<SysDictData> opsrList = DictUtils.getDictCache("order_opsr");
                    mapData2.put("1",opsrList);

                    mapData3.put("1","order_opsr");
                case "2":
                    List<SysDictData> wfsList = DictUtils.getDictCache("order_waiting_for_shipment");
                    mapData1.put("2",wfsList);

                    List<SysDictData> owfsList = DictUtils.getDictCache("order_owfs");
                    mapData2.put("2",owfsList);

                    mapData3.put("2","order_owfs");
                case "3":
                    List<SysDictData> sList = DictUtils.getDictCache("order_shipped");
                    mapData1.put("3",sList);

                    List<SysDictData> osList = DictUtils.getDictCache("order_os");
                    mapData2.put("3",osList);

                    mapData3.put("3","order_os");
                case "4":
                    List<SysDictData> fList = DictUtils.getDictCache("order_freezing");
                    mapData1.put("4",fList);

                    List<SysDictData> ofList = DictUtils.getDictCache("order_of");
                    mapData2.put("4",ofList);

                    mapData3.put("4","order_of");
                case "5":
                    List<SysDictData> oosList = DictUtils.getDictCache("order_out_of_stock");
                    mapData1.put("5",oosList);

                    List<SysDictData> ooosList = DictUtils.getDictCache("order_ooos");
                    mapData2.put("5",ooosList);

                    mapData3.put("5","order_ooos");
                case "6":
                    List<SysDictData> ppList = DictUtils.getDictCache("order_problem_piece");
                    mapData1.put("6",ppList);

                    List<SysDictData> oppList = DictUtils.getDictCache("order_opp");
                    mapData2.put("6",oppList);

                    mapData3.put("6","order_opp");
                case "7":
                    List<SysDictData> oList = DictUtils.getDictCache("order_obsolete");
                    mapData1.put("7",oList);

                    List<SysDictData> ooList = DictUtils.getDictCache("order_oo");
                    mapData2.put("7",ooList);

                    mapData3.put("7","order_oo");
                default:;
            }
        }
        bigMap.put("optData",mapData1);
        bigMap.put("signData",mapData2);
        bigMap.put("signType",mapData3);
        return bigMap;
    }

    /**
     * 更新订单自定义标记
     *
     * @param data json数据
     * @return 结果
     */
    public int updateOrderSign(JSONObject data){
        ArrayList ids = (ArrayList) data.get("ids");
        Long signType = Long.valueOf(data.getString("signType"));

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("ids",ids);

        Order order = new Order();
        order.setSignType(signType);
        order.setParams(mapData);

        return orderMapper.updateOrderValue(order);
    }

    /**
     * 更新客服备注
     *
     * @param data json数据
     * @return 结果
     */
    public int updateOrderRemarks(JSONObject data){
        ArrayList ids = (ArrayList) data.get("ids");
        String remarks = data.getString("remarks");

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("ids",ids);

        Order order = new Order();
        order.setCustomerServiceRemarks(remarks);
        order.setParams(mapData);

        return orderMapper.updateOrderValue(order);
    }

    /**
     * 提交发货审核
     *
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int updateOrderDa(Order order)
    {
        List<Order> orderList = orderMapper.selectOrderByIds(order);
        for (Order o:orderList) {
            o.setDeliveryWarehouseId(order.getDeliveryWarehouseId());
            o.setWarehouseDeliveryId(order.getWarehouseDeliveryId());
            int result = orderMapper.updateOrder(o);
        }
        return 1;
    }
}
