import request from '@/utils/request'

// 查询单位管理列表
export function listUnit(query) {
  return request({
    url: '/rchives/unit/list',
    method: 'get',
    params: query
  })
}

// 查询单位管理详细
export function getUnit(id) {
  return request({
    url: '/rchives/unit/' + id,
    method: 'get'
  })
}

// 新增单位管理
export function addUnit(data) {
  return request({
    url: '/rchives/unit',
    method: 'post',
    data: data
  })
}

// 修改单位管理
export function updateUnit(data) {
  return request({
    url: '/rchives/unit',
    method: 'put',
    data: data
  })
}

// 删除单位管理
export function delUnit(id) {
  return request({
    url: '/rchives/unit/' + id,
    method: 'delete'
  })
}
