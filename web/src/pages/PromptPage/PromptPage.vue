<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { onMounted, ref } from 'vue'
import { useQuasar } from 'quasar'

const route = useRoute()
const router = useRouter()
const $q = useQuasar()
const promptId = ref(0)
onMounted(() => {
  promptId.value = parseInt(route.params.promptId as string)
  if (!promptId.value) {
    $q.notify({
      type: 'warning',
      message: '无法解析当前提示词，3s 后回到搜索页'
    })
    setTimeout(() => {
      router.push('/content')
    }, 3000)
    return
  }
  console.log(promptId.value)
})
</script>

<template>
  <div>prompt</div>
</template>

<style scoped lang="scss">

</style>
