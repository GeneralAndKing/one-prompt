import { BaseEntity } from 'src/model/Base'

export interface Prompt extends BaseEntity{
  description?: string
  logo?: string
  image?: string[]
  content?: string
  instructions?: string
  verified?: boolean
}
