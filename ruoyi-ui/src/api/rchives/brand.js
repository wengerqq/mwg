import request from '@/utils/request'

// 查询品牌管理列表
export function listBrand(query) {
  return request({
    url: '/rchives/brand/list',
    method: 'post',
    params: query
  })
}

// 查询品牌管理详细
export function getBrand(id) {
  return request({
    url: '/rchives/brand/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增品牌管理
export function addBrand(data) {
  return request({
    url: '/rchives/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌管理
export function updateBrand(data) {
  return request({
    url: '/rchives/brand/edit',
    method: 'post',
    data: data
  })
}

// 删除品牌管理
export function delBrand(id) {
  return request({
    url: '/rchives/brand/remove',
    method: 'post',
    data:id instanceof Array?id:[id]
  })
}
