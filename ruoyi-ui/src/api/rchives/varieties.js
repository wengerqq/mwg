import request from '@/utils/request'

// 查询品类管理列表
export function listVarieties(query) {
  return request({
    url: '/rchives/varieties/list',
    method: 'get',
    params: query
  })
}

// 查询品类管理详细
export function getVarieties(id) {
  return request({
    url: '/rchives/varieties/' + id,
    method: 'get'
  })
}

// 新增品类管理
export function addVarieties(data) {
  return request({
    url: '/rchives/varieties',
    method: 'post',
    data: data
  })
}

// 修改品类管理
export function updateVarieties(data) {
  return request({
    url: '/rchives/varieties',
    method: 'put',
    data: data
  })
}

// 删除品类管理
export function delVarieties(id) {
  return request({
    url: '/rchives/varieties/' + id,
    method: 'delete'
  })
}
