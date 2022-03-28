import request from '@/utils/request'

// 查询产品资料管理列表
export function listProducts(query) {
  return request({
    url: '/rchives/products/list',
    method: 'post',
    params: query
  })
}

// 查询产品资料管理详细
export function getProducts(id) {
  return request({
    url: '/rchives/products/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增产品资料管理
export function addProducts(data) {
  return request({
    url: '/rchives/products',
    method: 'post',
    data: data
  })
}

// 修改产品资料管理
export function updateProducts(data) {
  return request({
    url: '/rchives/products/edit',
    method: 'post',
    data: data
  })
}

// 删除产品资料管理
export function delProducts(id) {
  return request({
    url: '/rchives/products/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}

//修改产品是否可用
export function changeProductUsable(id, usable) {
  const data = {
    id,
    usable
  }
  return request({
    url: '/rchives/products/changeUsable',
    method: 'post',
    data: data
  })
}
