import request from '@/utils/request'

// 查询 列表
export function listRoom(query) {
  return request({
    url: '/system/room/list',
    method: 'get',
    params: query
  })
}

// 查询 详细
export function getRoom(id) {
  return request({
    url: '/system/room/' + id,
    method: 'get'
  })
}

// 新增
export function addRoom(data) {
  return request({
    url: '/system/room',
    method: 'post',
    data: data
  })
}

// 修改
export function updateRoom(data) {
  return request({
    url: '/system/room',
    method: 'put',
    data: data
  })
}

// 删除
export function delRoom(id) {
  return request({
    url: '/system/room/' + id,
    method: 'delete'
  })
}
