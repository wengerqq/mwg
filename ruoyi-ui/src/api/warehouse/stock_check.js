import request from '@/utils/request'

// 查询库存查询列表
export function listStock_check(query) {
  return request({
    url: '/warehouse/stock_check/list',
    method: 'post',
    data: query
  })
}

// 查询库存查询详细
export function getStock_check(id) {
  return request({
    url: '/warehouse/stock_check/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增库存查询
export function addStock_check(data) {
  return request({
    url: '/warehouse/stock_check',
    method: 'post',
    data: data
  })
}

// 修改库存查询
export function updateStock_check(data) {
  return request({
    url: '/warehouse/stock_check/edit',
    method: 'post',
    data: data
  })
}

// 删除库存查询
export function delStock_check(id) {
  return request({
    url: '/warehouse/stock_check/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}
