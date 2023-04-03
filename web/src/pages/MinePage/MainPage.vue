<template>
  <div class="mine text-center">
    <q-avatar class="mine-avatar shadow-21">
      <q-img ratio="1" fit="fill" :src="`https://www.loliapi.com/acg/?${new Date().toISOString()}`"/>
    </q-avatar>
    <div class="mine-name text-h4">旭旭宝宝人已虚</div>
    <div class="mine-number">
      <span>
        1000 <span class="mine-tip">提示词</span>
      </span>
      <span style="margin-left: 50px">
        1000 <span class="mine-tip">喜欢</span>
      </span>
    </div>
  </div>
  <div class="mine-main">
    <q-tabs
      v-model="tab"
      no-caps
      outside-arrows
      mobile-arrows
      align="left"
      class="mine-tab shadow-2"
    >
      <q-tab name="prompt" label="提示词"/>
      <q-tab name="line" label="喜欢"/>
    </q-tabs>
    <q-card class="mine-content q-mt-none q-gutter-lg row justify-start q-gutter-sm">
      <prompt-info v-for="item in prompt" :prompt="item" :key="item.id"/>
    </q-card>
  </div>
</template>

<script setup lang="ts">

import { ref, onMounted } from 'vue'
import { PromptUser } from '@/model/Prompt'
import PromptInfo from '@/components/PromptInfo.vue'

const prompt = ref<PromptUser[]>([])
const tab = ref('prompt')

onMounted(() => {
  const list: PromptUser[] = []
  for (let i = 0; i < 10; i++) {
    list.push({
      id: i,
      name: `test-${i}`,
      description: 'Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. Suits occurring reductions accessible dennis. ',
      category: 'box',
      user: 'Dolores Bond',
      avtar: `https://www.loliapi.com/acg/?0${(i % 5) + 1}.jpeg`,
      like: i * 5,
      logo: `https://www.loliapi.com/acg/?a=${new Date().toISOString() + i}`
    })
  }
  prompt.value = list
})
</script>

<style scoped lang="scss">
.mine {
  margin-top: 80px;

  .mine-avatar {
    width: 120px;
    height: 120px;
    border-radius: 50%;
  }

  .mine-name {
    margin-top: 24px;
    font-size: 38px;
    line-height: 53px;
    font-weight: 500;
  }

  .mine-number {
    margin-top: 16px;
    font-size: 32px;
    line-height: 45px;
    font-weight: 500;

    .mine-tip {
      font-size: 20px;
      font-weight: 300;
      line-height: 28px;
      margin-left: 6px;
    }
  }
}

.mine-main {
  padding: 16px 140px;

  .mine-tab {
    margin-left: -25px;
  }

  .mine-content {
    margin-top: 32px;
    background: rgba(169, 197, 255, 0.2);;
  }
}

</style>
