import { boot } from 'quasar/wrappers'
import axios, { AxiosInstance } from 'axios'
import { useApplicationStore } from '@/stores/useApplicationStore'

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
  }
}

// Be careful when using SSR for cross-request state pollution
// due to creating a Singleton instance here;
// If any client changes this (global) instance, it might be a
// good idea to move this instance creation inside of the
// "export default () => {}" function below (which runs individually
// for each client)

let baseURL = '/api'
if (process.env.DEV && process.env.MOCK_ENABLE) {
  baseURL = process.env.MOCK_BASE_URL || '/api'
}
const api = axios.create({
  baseURL,
  responseType: 'json'
})

api.interceptors.request.use(
  (config) => {
    const applicationStore = useApplicationStore()

    if (applicationStore && applicationStore.token && applicationStore.token.accessToken) {
      config.headers.Authorization = `Bearer ${applicationStore.token.accessToken}`
    }

    if (process.env.DEV && process.env.MOCK_ENABLE) {
      config.headers.apifoxToken = process.env.MOCK_TOKEN
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

api.interceptors.response.use(
  (response) => {
    return response.data
  },
  (error) => {
    let errorMessage = '发生了一个错误，请稍后再试。'

    if (error.response) {
      const data = error.response.data
      errorMessage = data.message || data.error
    }

    return Promise.reject(new Error(errorMessage))
  }
)

export default boot(({ app }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
})

export { api }
