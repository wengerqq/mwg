import request from '@/utils/request'

// 查询仓库设置列表
export function listWarehouse_settings(query) {
  return request({
    url: '/warehouse/warehouse_settings/list',
    method: 'post',
    params: query
  })
}

// 查询仓库设置详细
export function getWarehouse_settings(id) {
  return request({
    url: '/warehouse/warehouse_settings/getInfo',
    method: 'post',
    data:Number(id)
  })
}

// 新增仓库设置
export function addWarehouse_settings(data) {
  return request({
    url: '/warehouse/warehouse_settings',
    method: 'post',
    data: data
  })
}

// 修改仓库设置
export function updateWarehouse_settings(data) {
  return request({
    url: '/warehouse/warehouse_settings/edit',
    method: 'post',
    data: data
  })
}

// 删除仓库设置
export function delWarehouse_settings(id) {
  return request({
    url: '/warehouse/warehouse_settings/remove',
    method: 'post',
    data: id instanceof Array?id:[id]
  })
}
