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
        <prompt-info v-for="item in prompt" :key="`item-${item.id}`" class="item fit" :prompt="item"/>
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
import { PromptUser } from '@/model/Prompt'
import PromptInfo from '@/components/PromptInfo.vue'

const prompt = ref<PromptUser[]>([])

const onLoad = (index: number, done: (stop?: boolean) => void) => {
  // TODO: 加载数据
  setTimeout(() => {
    done()
  }, 5000)
}
onMounted(() => {
  const list: PromptUser[] = []
  // TODO  API 数据
  for (let i = 0; i < 10; i++) {
    list.push({
      id: i,
      name: `test-${i}`,
      description: 'Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. ',
      category: 'box',
      user: 'Dolores Bond',
      avtar: `https://resources.echocow.cn/prompt/0${(i % 5) + 1}.jpeg`,
      like: i * 5,
      logo: `https://img.paulzzh.com/touhou/random?a=${new Date().toISOString() + i}`
    })
  }
  prompt.value = list
})

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
