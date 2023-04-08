<template>
  <q-intersection
    once
    transition="scale"
  >
    <q-img
      class="list-item cursor-pointer"
      :src="prompt.logo"
    >
      <div class="list-action absolute-bottom">
        <div class="ellipsis-2-lines">{{ prompt.description }}</div>
        <div class="flex items-center q-mt-xs">
          <q-avatar style="width: 26px;height: 26px">
            <img :alt="prompt.name" :src="prompt.avtar">
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
</template>

<script setup lang="ts">

import { PromptUser } from '@/model/Prompt'

interface Props {
  prompt: PromptUser
}

const props = defineProps<Props>()

</script>

<style scoped lang="scss">

.list-item {
  height: 350px;
  border-radius: 16px;
}

</style>
