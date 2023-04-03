<template>
  <div id="dashboard">
    <q-breadcrumbs active-color="white" class="text-white text-h5" gutter="sm">
      <q-breadcrumbs-el label="Home"/>
      <q-breadcrumbs-el label="Components"/>
      <q-breadcrumbs-el label="Breadcrumbs"/>
    </q-breadcrumbs>
    <q-separator class="q-mt-md" color="gray"/>
    <div class="content">
      <div class="q-mt-none q-gutter-lg row justify-start q-gutter-sm">
        <template v-for="item in prompt" :key="`item-${item.id}`">
          <prompt-info :prompt="item"/>
        </template>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { onMounted, ref } from 'vue'
import { PromptUser } from '@/model/Prompt'
import PromptInfo from '@/components/PromptInfo.vue'

const prompt = ref<PromptUser[]>([])

onMounted(() => {
  const list: PromptUser[] = []
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

  .list-action {
    background: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, #000000 100%);
    height: 100px;
  }
}
</style>
