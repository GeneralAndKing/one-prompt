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

export interface SearchData {
  avatar: string | null;
  id: number;
  image: string | null;
  author: string;
  name: string;
  star: number;
  tag: string[] | null;
  description: string;
}

export interface SearchResponse {
  list: SearchData[];
  pageSize: number;
  pageNow: number;
  total: number;
  totalPage: number;
}

export const search = (param: ChannelSearchParam): Promise<SearchResponse> =>
  api.post('/search', param)

export enum ChannelFilterType {
  SELECT = 'SELECT',
  CHECKBOX = 'CHECKBOX',
  TOGGLE = 'TOGGLE'
}

export interface ChannelFilterOptions {
  id: number;
  name: string;
  value: string;
}

export interface ChannelFilter {
  id: number;
  name: string;
  type: ChannelFilterType;
  options?: ChannelFilterOptions[]
}

export interface ChannelTab {
  filters: ChannelFilter[];
  id: number;
  name: string;
}

export const getAllChannel = (): Promise<ChannelTab[]> =>
  api.get('/channelTab')
