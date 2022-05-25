import request from '@/utils/request'

// 查询订单管理列表
export function listOrder(query) {
  return request({
    url: '/sales/order/list',
    method: 'post',
    params: query
  })
}

// 查询订单管理详细
export function getOrder(id) {
  return request({
    url: '/sales/order/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增订单管理
export function addOrder(data) {
  return request({
    url: '/sales/order',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrder(data) {
  return request({
    url: '/sales/order/edit',
    method: 'post',
    data: data
  })
}

// 删除订单管理
export function delOrder(id) {
  return request({
    url: '/sales/order/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}

// 查询订单状态数据
export function getOrderStatusList() {
  return request({
    url: '/sales/order/getOrderStatusList',
    method: 'post',
  })
}

// 更新订单自定义标记
export function updateOrderSign(data) {
  return request({
    url: '/sales/order/updateOrderSign',
    method: 'post',
    data: data
  })
}

// 更新客服备注
export function updateOrderRemarks(data) {
  return request({
    url: '/sales/order/updateOrderRemarks',
    method: 'post',
    data: data
  })
}

// 提交发货审核
export function updateOrderDa(data) {
  return request({
    url: '/sales/order/updateOrderDa',
    method: 'post',
    data: data
  })
}

// 更新订单状态
export function updateOrderStatus(data) {
  return request({
    url: '/sales/order/updateOrderValue',
    method: 'post',
    data: data
  })
}

// 截单提交
export function updateOrderCut(data) {
  return request({
    url: '/sales/order/updateOrderCut',
    method: 'post',
    data: data
  })
}

