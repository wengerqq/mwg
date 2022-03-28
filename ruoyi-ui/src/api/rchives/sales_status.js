import request from '@/utils/request'

// 查询产品销售状态管理列表
export function listSales_status(query) {
  return request({
    url: '/rchives/sales_status/list',
    method: 'get',
    params: query
  })
}

// 查询产品销售状态管理详细
export function getSales_status(id) {
  return request({
    url: '/rchives/sales_status/' + id,
    method: 'get'
  })
}

// 新增产品销售状态管理
export function addSales_status(data) {
  return request({
    url: '/rchives/sales_status',
    method: 'post',
    data: data
  })
}

// 修改产品销售状态管理
export function updateSales_status(data) {
  return request({
    url: '/rchives/sales_status',
    method: 'put',
    data: data
  })
}

// 删除产品销售状态管理
export function delSales_status(id) {
  return request({
    url: '/rchives/sales_status/' + id,
    method: 'delete'
  })
}
