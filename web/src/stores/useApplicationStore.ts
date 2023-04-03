import { defineStore } from 'pinia'
import { UserToken } from '@/model/Auth'
import { ref } from 'vue'

export const useApplicationStore = defineStore('application', () => {
  const token = ref<UserToken | null>(null)

  const setToken = (val: UserToken) => {
    token.value = val
  }

  return {
    token,
    setToken
  }
})
