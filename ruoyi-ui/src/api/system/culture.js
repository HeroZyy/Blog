import request from '@/utils/request'

// 查询茶文化列表
export function listCulture(query) {
  return request({
    url: '/system/culture/list',
    method: 'get',
    params: query
  })
}

// 查询茶文化详细
export function getCulture(id) {
  return request({
    url: '/system/culture/' + id,
    method: 'get'
  })
}

// 新增茶文化
export function addCulture(data) {
  return request({
    url: '/system/culture',
    method: 'post',
    data: data
  })
}

// 修改茶文化
export function updateCulture(data) {
  return request({
    url: '/system/culture',
    method: 'put',
    data: data
  })
}

// 删除茶文化
export function delCulture(id) {
  return request({
    url: '/system/culture/' + id,
    method: 'delete'
  })
}
