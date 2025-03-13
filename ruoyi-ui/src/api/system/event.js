import request from '@/utils/request'

// 查询活动管理列表
export function listEvent(query) {
  return request({
    url: '/system/event/list',
    method: 'get',
    params: query
  })
}

// 查询活动管理详细
export function getEvent(id) {
  return request({
    url: '/system/event/' + id,
    method: 'get'
  })
}

// 新增活动管理
export function addEvent(data) {
  return request({
    url: '/system/event',
    method: 'post',
    data: data
  })
}

// 修改活动管理
export function updateEvent(data) {
  return request({
    url: '/system/event',
    method: 'put',
    data: data
  })
}

// 删除活动管理
export function delEvent(id) {
  return request({
    url: '/system/event/' + id,
    method: 'delete'
  })
}
