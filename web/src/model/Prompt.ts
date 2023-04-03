import { BaseEntity } from 'src/model/Base'

export interface Prompt extends BaseEntity{
  description?: string
  name?: string
  logo?: string
  image?: string[]
  content?: string
  instructions?: string
  verified?: boolean
}

export interface PromptUser extends Prompt{
  category: string
  user: string
  avtar: string
  like: number
}
