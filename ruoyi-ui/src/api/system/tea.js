import request from '@/utils/request'

// 查询tea列表
export function listTea(query) {
  return request({
    url: '/system/tea/list',
    method: 'get',
    params: query
  })
}

// 查询tea详细
export function getTea(id) {
  return request({
    url: '/system/tea/' + id,
    method: 'get'
  })
}

// 新增tea
export function addTea(data) {
  return request({
    url: '/system/tea',
    method: 'post',
    data: data
  })
}

// 修改tea
export function updateTea(data) {
  return request({
    url: '/system/tea',
    method: 'put',
    data: data
  })
}

// 删除tea
export function delTea(id) {
  return request({
    url: '/system/tea/' + id,
    method: 'delete'
  })
}
