<template>
  <div id="dashboard">
    <q-breadcrumbs active-color="white" class="text-white text-h5" gutter="sm">
      <q-breadcrumbs-el label="Home"/>
      <q-breadcrumbs-el label="Components"/>
      <q-breadcrumbs-el label="Breadcrumbs"/>
    </q-breadcrumbs>
    <q-separator class="q-mt-md" color="gray"/>
    <div class="content">
      <div class="row justify-center q-gutter-sm">
        <q-intersection
          v-for="item in prompt"
          :key="`item-${item.id}`"
          once
          transition="scale"
          class="example-item"
        >
          <q-img :src="item.image">
            <div class="absolute-bottom text-subtitle1 text-center">
              Caption
            </div>
          </q-img>
        </q-intersection>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { onMounted, ref } from 'vue'

interface Prompt {
  id: number
  name: string
  description: string
  category: string
  user: string
  avtar: string
  like: number
  image: string
}

const prompt = ref<Prompt[]>([])

onMounted(() => {
  const list: Prompt[] = []
  for (let i = 0; i < 19; i++) {
    list.push({
      id: i,
      name: `test-${i}`,
      description: 'Suits occurring reductions accessible dennis. ',
      category: 'box',
      user: 'Dolores Bond',
      avtar: `https://resources.echocow.cn/prompt/0${(i % 5) + 1}.jpeg`,
      like: i * 5,
      image: `https://resources.echocow.cn/prompt/0${(i % 5) + 1}.jpeg`
    })
  }
  prompt.value = list
})

</script>

<style scoped lang="scss">
#dashboard {
  padding: 60px;

  .example-item {
    width: 360px;
  }
}
</style>
