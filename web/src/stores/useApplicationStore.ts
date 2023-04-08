import { defineStore } from 'pinia'
import { UserToken } from '@/model/Auth'
import { computed, ref } from 'vue'

export const useApplicationStore = defineStore('application', () => {
  const token = ref<UserToken | null>(null)

  const setToken = (val: UserToken) => {
    token.value = val
  }

  const isLogin = computed(() => {
    return token.value !== null && token.value.accessToken !== null
  })

  return {
    token,
    setToken,
    isLogin
  }
}, {
  persist: true
})
