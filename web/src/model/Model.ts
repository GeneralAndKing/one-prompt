import { BaseEntity } from 'src/model/Base'
import { Category } from 'src/model/Category'

export interface Model extends BaseEntity {
  name?: string
  description?: string
  logo?: string
  categories?: Category
}
