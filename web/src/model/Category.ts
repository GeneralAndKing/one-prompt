import { BaseEntity } from 'src/model/Base'
import { Prompt } from 'src/model/Prompt'

export interface Category extends BaseEntity{
  description?: string
  prompts?: Prompt[]
}
