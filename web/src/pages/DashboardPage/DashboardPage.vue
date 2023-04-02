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
        <q-intersection
          v-for="item in prompt"
          :key="`item-${item.id}`"
          once
          transition="scale"
        >
          <q-img
            class="list-item cursor-pointer"
            :src="item.image"
          >
            <div class="list-action absolute-bottom">
              <div class="ellipsis-2-lines">{{ item.description }}</div>
              <div class="flex items-center q-mt-xs">
                <q-avatar style="width: 26px;height: 26px">
                  <img :alt="item.name" :src="item.avtar">
                </q-avatar>
                <div class="q-ml-sm">是的发送到发</div>
                <q-space/>
                <div class="row items-center">
                  <q-btn class="q-pr-sm" size="10px" rounded icon="favorite_border"/>
                  <div>2000</div>
                </div>
              </div>
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
  for (let i = 0; i < 10; i++) {
    list.push({
      id: i,
      name: `test-${i}`,
      description: 'Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. ',
      category: 'box',
      user: 'Dolores Bond',
      avtar: `https://resources.echocow.cn/prompt/0${(i % 5) + 1}.jpeg`,
      like: i * 5,
      image: `https://random-picture.vercel.app/api?category=dongman&type=302&a=${new Date().toISOString() + i}`
    })
  }
  prompt.value = list
})

</script>

<style scoped lang="scss">
#dashboard {
  padding: 60px;

  .list-item {
    height: 340px;
    width: 360px;
    border-radius: 16px;
  }

  .list-action {
    background: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, #000000 100%);
    height: 100px;
  }
}
</style>
