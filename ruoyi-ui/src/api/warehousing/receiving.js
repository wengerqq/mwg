import request from '@/utils/request'

// 查询收货管理列表
export function listReceiving(query) {
  return request({
    url: '/warehousing/receiving/list',
    method: 'post',
    params: query
  })
}

// 查询收货管理详细
export function getReceiving(id) {
  return request({
    url: '/warehousing/receiving/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增收货管理
export function addReceiving(data) {
  return request({
    url: '/warehousing/receiving',
    method: 'post',
    data: data
  })
}

// 修改收货管理
export function updateReceiving(data) {
  return request({
    url: '/warehousing/receiving/edit',
    method: 'post',
    data: data
  })
}

// 删除收货管理
export function delReceiving(id) {
  return request({
    url: '/warehousing/receiving/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}

//未到货
export function undelivered(id){
  return request({
    url: '/warehousing/receiving/undelivered',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}

// 修改收货管理
export function receiving(data) {
  return request({
    url: '/warehousing/receiving/receiving',
    method: 'post',
    data: data
  })
}
