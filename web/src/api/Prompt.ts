import { api } from '@/boot/axios'

export interface SearchParam {
  /**
   * 过滤器，分类列表
   */
  categories: number[] | null;
  /**
   * 关键字
   */
  keywords: null | string;
  /**
   * ai 模型
   */
  modelId: number | null;
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
  sort: string[] | null;
}

export interface SearchData {
  /**
   * 作者
   */
  author: string;
  /**
   * 作者头像
   */
  avatar: string;
  /**
   * 描述
   */
  description: string;
  /**
   * 自增 id
   */
  id: number;
  /**
   * 图片，为 null 不显示
   */
  image: null | string;
  /**
   * 标题
   */
  name: string;
  /**
   * star 数
   */
  star: number;
  /**
   * 标签
   */
  tag: string[] | null;
}

export interface SearchResponse {
  list: SearchData[];
  pageSize: number;
  pageNow: number;
  total: number;
  totalPage: number;
}

export const search = (param: SearchParam): Promise<SearchResponse> =>
  api.get('/prompt/search', { params: param })

export interface ModelCategoryFilter {
  id: number;
  name: string;
  description: string;
}

export interface Category {
  description: string;
  id: number;
  name: string;
}

export interface ModelTab {
  categories: Category[];
  description: string;
  id: number;
  logo: string;
  name: string;
}

export const getAllModel = (): Promise<ModelTab[]> =>
  api.get('/models')
