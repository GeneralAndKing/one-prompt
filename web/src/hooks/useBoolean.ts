import { ref } from 'vue'

export default (defaultValue = false) => {
  const state = ref<boolean>(defaultValue)
  return {
    state,
    setTrue: () => {
      state.value = true
    },
    setFalse: () => {
      state.value = false
    },
    toggle: () => {
      state.value = !state.value
    },
    set: (value: boolean) => {
      state.value = value
    }
  }
}
