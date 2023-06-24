import { api } from '@/boot/axios'

export interface ChannelSearchParam {
  /**
   * ai 引擎
   */
  engine: null | string;
  /**
   * 过滤器
   */
  filters: { [key: string]: any } | null;
  /**
   * 关键字
   */
  keywords: null | string;
  /**
   * 当前页码，从 1 开始
   */
  pageNow: number | null;
  /**
   * 分页大小
   */
  pageSize: number | null;
  /**
   * 趋势，数据排序
   */
  trending: string[] | null;
}

export interface SearchResponse {
  avatar: string | null,
  id: 8,
  image: string | null,
  author: string,
  name: string,
  star: number,
  tag: string[] | null,
  description: string
}

export const search = (param: ChannelSearchParam): Promise<SearchResponse[]> =>
  api.post('/search', param)
