<template>
  <q-intersection
    once
    transition="scale"
  >
    <q-img
      class="list-item cursor-pointer"
      :src="props.prompt.logo"
    >
      <div class="list-action absolute-bottom">
        <div class="ellipsis-2-lines">{{ prompt.description }}</div>
        <div class="flex items-center q-mt-xs">
          <q-avatar style="width: 26px;height: 26px">
            <img :alt="prompt.name" :src="prompt.avatar">
          </q-avatar>
          <div class="q-ml-sm">是的发送到发</div>
          <q-space/>
          <div class="row items-center">
            <q-btn dense size="10px" rounded icon="favorite_border"/>
            <div>2000</div>
          </div>
        </div>
      </div>
    </q-img>
  </q-intersection>
</template>

<script setup lang="ts">

import { PromptComplete } from '@/model/PromptComplete'

interface Props {
  prompt: PromptComplete
}

const props = defineProps<Props>()

</script>

<style scoped lang="scss">

.list-item {
  height: 300px;
  border-radius: 16px;
}

.list-action {
  background: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, #000000 100%);
  height: 100px;
}

</style>
