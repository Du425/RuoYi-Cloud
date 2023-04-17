import request from '@/utils/request'

// 查询 列表
export function listOrderFinance(query) {
  return request({
    url: '/system/order/finance',
    method: 'get',
    params: query
  })
}
