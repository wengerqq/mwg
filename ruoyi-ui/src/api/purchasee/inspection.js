import request from '@/utils/request'

// 查询采购质检列表
export function listInspection(query) {
  return request({
    url: '/purchasee/inspection/list',
    method: 'post',
    params: query
  })
}

// 查询采购质检详细
export function getInspection(id) {
  return request({
    url: '/purchasee/inspection/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增采购质检
export function addInspection(data) {
  return request({
    url: '/purchasee/inspection',
    method: 'post',
    data: data
  })
}

// 修改采购质检
export function updateInspection(data) {
  return request({
    url: '/purchasee/inspection/edit',
    method: 'post',
    data: data
  })
}

// 删除采购质检
export function delInspection(id) {
  return request({
    url: '/purchasee/inspection/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}
