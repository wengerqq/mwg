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
import com.ruoyi.products.domain.Products;
import com.ruoyi.products.mapper.ProductsMapper;
import com.ruoyi.stock_check.domain.StockCheck;
import com.ruoyi.stock_check.mapper.StockCheckMapper;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    @Autowired
    private StockCheckMapper stockCheckMapper;

    @Autowired
    private ProductsMapper productsMapper;

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
    public ArrayList<String> updateOrderDa(Order order)
    {
        List<Order> orderList = orderMapper.selectOrderByIds(order);
        int successNum = 0;
        int failNum = 0;
        String five = "";
        String six = "";
        String two = "";
        ArrayList<String> msgData = new ArrayList<String>();
        for (Order o:orderList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderKeyId(o.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetailList(orderDetail);
            int a = 0;
            int b = 0;
            if(orderDetailList.size()>0){
                for(OrderDetail od:orderDetailList){
                    Products products = productsMapper.selectProductsBySku(od.getProductSku());
                    if(products==null){
                        od.setAbnormal("产品SKU不存在");
                        a++;
                    }else{
                        StockCheck stockCheck = new StockCheck();
                        stockCheck.setProductsId(products.getId());
                        List<StockCheck> stockCheckList = stockCheckMapper.selectStockCheckList(stockCheck);
                        if(stockCheckList==null||stockCheckList.get(0).getSaleableQuantity()<od.getQuantity()){
                            od.setAbnormal("库存不足或没有采购记录");
                            b++;
                        }else{
                            od.setAbnormal("");
                        }
                    }
                    orderDetailMapper.updateOrderDetail(od);
                }
            }else{
                a=1;
            }
            if(a > 0){
                o.setStatus(6L);
                six += "<span style='font-weight: bold;'>"+o.getOrderId()+"</span> 提交失败，原因：<span style='color:red;'>产品SKU不存在【问题件】</span><br/>";
                failNum++;
            }else if(b>0){
                o.setStatus(5L);
                five += "<span style='font-weight: bold;'>"+o.getOrderId()+"</span> 提交失败，原因：<span style='color:red;'>库存不足或没有采购记录【缺货】</span><br/>";
                failNum++;
            }else{
                o.setStatus(2L);
                o.setDeliveryWarehouseId(order.getDeliveryWarehouseId());
                o.setWarehouseDeliveryId(order.getWarehouseDeliveryId());
                two += "<span style='font-weight: bold;'>"+o.getOrderId()+"</span> <span style='color:green;'>提交成功</span><br/>";
                successNum++;
            }
            orderMapper.updateOrder(o);
        }
        if(five!="")msgData.add(five);
        if(six!="")msgData.add(six);
        if(two!="")msgData.add(two);
        msgData.add("共 <span style='color:blue;'>"+orderList.size()+"</span> 条订单，成功 <span style='color:green;'>"+successNum+"</span> 条，失败 <span style='color:red;'>"+failNum+"</span> 条");
        return msgData;
    }

    /**
     * 修改订单状态
     *
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int updateOrderValue(Order order)
    {
        return orderMapper.updateOrderValue(order);
    }

    /**
     * 截单提交
     *
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public ArrayList<String> updateOrderCut(Order order)
    {
        List<Order> orderList = orderMapper.selectOrderByIds(order);
        int successNum = 0;
        int failNum = 0;
        String six = "";
        ArrayList<String> msgData = new ArrayList<String>();
        for (Order o:orderList) {
            o.setStatus(6L);
            o.setSignType(order.getSignType());
            o.setAbnormal(order.getAbnormal());
            orderMapper.updateOrder(o);
            six += o.getOrderId()+" 已成功拦截<br/>";
            successNum++;
        }
        msgData.add(six+"共 <span style='color:blue;'>"+orderList.size()+"</span> 条订单，成功 <span style='color:green;'>"+successNum+"</span> 条，失败 <span style='color:red;'>"+failNum+"</span> 条");
        return msgData;
    }
}
