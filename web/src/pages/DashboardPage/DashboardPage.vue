<template>
  <div id="dashboard">
    <q-breadcrumbs active-color="white" class="text-white text-h5" gutter="sm">
      <q-breadcrumbs-el label="Home"/>
      <q-breadcrumbs-el label="Components"/>
      <q-breadcrumbs-el label="Breadcrumbs"/>
    </q-breadcrumbs>
    <q-separator class="q-mt-md" color="gray"/>
    <q-infinite-scroll @load="onLoad" :offset="750">
      <div class="content">
        <prompt-info v-for="item in prompt.content" :key="`item-${item.id}`" class="item fit" :prompt="item"/>
      </div>
      <template v-slot:loading>
        <div class="row justify-center q-my-md">
          <q-spinner-dots color="primary" size="40px"/>
        </div>
      </template>
    </q-infinite-scroll>
  </div>
</template>

<script lang="ts" setup>

import { onMounted, ref } from 'vue'
import PromptInfo from '@/components/PromptInfo.vue'
import { dashboardData } from '@/api/Dashboard'
import { Page } from '@/model/Base'
import { PromptComplete } from '@/model/PromptComplete'

const prompt = ref<Page<PromptComplete>>({
  content: [],
  totalPages: 0,
  totalElements: 0,
  first: true,
  last: true,
  size: 12,
  number: 0,
  numberOfElements: 0
})

const onLoad = (index: number, done: (stop?: boolean) => void) => {
  // TODO: 加载数据
  setTimeout(() => {
    done()
  }, 5000)
}
onMounted(() => {
  handleList()
})

const handleList = async () => {
  prompt.value = await dashboardData([], '')
}

</script>

<style scoped lang="scss">
#dashboard {
  padding: 60px;

  .content {
    display: grid;
    justify-content: space-between;
    grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
    grid-gap: 30px;
  }

}
</style>
