export interface NavigationCategory {
  id?: number
  sort?: number
  name?: string
  value?: boolean
}

export interface Navigation extends NavigationCategory{
  categories?: NavigationCategory[]
}
