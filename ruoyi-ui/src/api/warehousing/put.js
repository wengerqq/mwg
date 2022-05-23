import request from '@/utils/request'

// 查询上架管理列表
export function listPut(query) {
  return request({
    url: '/warehousing/put/list',
    method: 'post',
    params: query
  })
}

// 查询上架管理详细
export function getPut(id) {
  return request({
    url: '/warehousing/put/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增上架管理
export function addPut(data) {
  return request({
    url: '/warehousing/put',
    method: 'post',
    data: data
  })
}

// 修改上架管理
export function updatePut(data) {
  return request({
    url: '/warehousing/put/edit',
    method: 'post',
    data: data
  })
}

// 删除上架管理
export function delPut(id) {
  return request({
    url: '/warehousing/put/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}

// 根据采购ID查询上架管理详细
export function getPurchaseKeyIdPut(purchaseKeyId) {
  return request({
    url: '/warehousing/put/getPurchaseKeyIdInfo',
    method: 'post',
    data:Number(purchaseKeyId)
  })
}

// 确认上架
export function updatePutList(data) {
  return request({
    url: '/warehousing/put/updatePutList',
    method: 'post',
    data: data
  })
}

// 取消上架
export function cancelPutList(data) {
  return request({
    url: '/warehousing/put/cancelPutList',
    method: 'post',
    data: data
  })
}
