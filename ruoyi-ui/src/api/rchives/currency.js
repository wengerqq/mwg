import request from '@/utils/request'

// 查询币种管理列表
export function listCurrency(query) {
  return request({
    url: '/rchives/currency/list',
    method: 'get',
    params: query
  })
}

// 查询币种管理详细
export function getCurrency(id) {
  return request({
    url: '/rchives/currency/' + id,
    method: 'get'
  })
}

// 新增币种管理
export function addCurrency(data) {
  return request({
    url: '/rchives/currency',
    method: 'post',
    data: data
  })
}

// 修改币种管理
export function updateCurrency(data) {
  return request({
    url: '/rchives/currency',
    method: 'put',
    data: data
  })
}

// 删除币种管理
export function delCurrency(id) {
  return request({
    url: '/rchives/currency/' + id,
    method: 'delete'
  })
}
