import request from '@/utils/request'

// 查询种植技术列表
export function listTea_tech(query) {
  return request({
    url: '/system/tea_tech/list',
    method: 'get',
    params: query
  })
}

// 查询种植技术详细
export function getTea_tech(id) {
  return request({
    url: '/system/tea_tech/' + id,
    method: 'get'
  })
}

// 新增种植技术
export function addTea_tech(data) {
  return request({
    url: '/system/tea_tech',
    method: 'post',
    data: data
  })
}

// 修改种植技术
export function updateTea_tech(data) {
  return request({
    url: '/system/tea_tech',
    method: 'put',
    data: data
  })
}

// 删除种植技术
export function delTea_tech(id) {
  return request({
    url: '/system/tea_tech/' + id,
    method: 'delete'
  })
}
