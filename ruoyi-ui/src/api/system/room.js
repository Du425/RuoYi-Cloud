import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 新增房间
export function addRoom(data) {
  return request({
    url: '/system/room',
    method: 'post',
    data: data
  })
}
