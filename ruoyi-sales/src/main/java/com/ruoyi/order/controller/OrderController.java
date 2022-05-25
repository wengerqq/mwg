package com.ruoyi.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysDictType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.Order;
import com.ruoyi.order.service.IOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@RestController
@RequestMapping("/sales/order")
public class OrderController extends BaseController
{
    @Autowired
    private IOrderService orderService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('sales:order:list')")
    @PostMapping("/list")
    public TableDataInfo list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('sales:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order)
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sales:order:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(orderService.selectOrderById(id));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('sales:order:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Order order)
    {
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('sales:order:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Order order)
    {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('sales:order:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(orderService.deleteOrderByIds(ids));
    }

    /**
     * 查询订单状态数据
     */
    @PostMapping("/getOrderStatusList")
    public AjaxResult getOrderStatusList()
    {
        Map<String,Object> orderStatusList = orderService.getOrderStatusList();
        return AjaxResult.success(orderStatusList);
    }

    /**
     * 更新订单自定义标记
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderSign")
    public AjaxResult updateOrderSign(@RequestBody JSONObject data)
    {
        return toAjax(orderService.updateOrderSign(data));
    }

    /**
     * 更新客服备注
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderRemarks")
    public AjaxResult updateOrderRemarks(@RequestBody JSONObject data)
    {
        return toAjax(orderService.updateOrderRemarks(data));
    }

    /**
     * 提交发货审核
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderDa")
    public AjaxResult updateOrderDa(@RequestBody Order order)
    {
        ArrayList<String> msgData = orderService.updateOrderDa(order);
        return toAjax(1,msgData);
    }

    /**
     * 更新订单某字段值
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderValue")
    public AjaxResult updateOrderValue(@RequestBody Order order)
    {
        return toAjax(orderService.updateOrderValue(order));
    }

    /**
     * 截单提交
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderCut")
    public AjaxResult updateOrderCut(@RequestBody Order order)
    {
        ArrayList<String> msgData = orderService.updateOrderCut(order);
        return toAjax(1,msgData);
    }
}
