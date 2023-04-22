export interface BaseEntity {
  id?: number
  createTime?: string
  createUser?: string
  updateTime?: string
  updateUser?: string
  remark?: string
  sort?: number
}

export interface Page<T> {
  content: T[]
  totalPages: number
  totalElements: number
  first: boolean
  last: boolean
  size: number
  number: number
  numberOfElements: number
}
