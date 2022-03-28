import request from '@/utils/request'

// 查询采购确认列表
export function listPurchase(query) {
  return request({
    url: '/purchase/purchase/list',
    method: 'post',
    params: query
  })
}

// 查询采购确认详细
export function getPurchase(purchaseId) {
  return request({
    url: '/purchase/purchase/getInfo',
    method: 'post',
    data:Number(purchaseId)
  })
}

// 新增采购确认
export function addPurchase(data) {
  return request({
    url: '/purchase/purchase',
    method: 'post',
    data: data
  })
}

// 修改采购确认
export function updatePurchase(data) {
  return request({
    url: '/purchase/purchase/edit',
    method: 'post',
    data: data
  })
}

// 删除采购确认
export function delPurchase(purchaseId) {
  return request({
    url: '/purchase/purchase/remove',
    method: 'post',
    data: purchaseId instanceof Array?purchaseId:[purchaseId]
  })
}
