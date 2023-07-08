<script setup lang="ts">

import { SearchParam, SearchResponse } from '@/api/Prompt'

interface Props {
  data: SearchResponse;
  loading: boolean;
  form: SearchParam;
}

interface Emits {
  (e: 'handleSearch', value: number): void
}

const props = defineProps<Props>()

const emit = defineEmits<Emits>()

const handleSearch = (value: KeyboardEvent) => {
  console.log(value)
  emit('handleSearch', value.detail)
}

</script>

<template>
  <div v-show="!props.loading" class="data-list">
    <q-card v-for="item in data.list" :key="item.id" class="col-6">
      <q-card-section class="flex column full-height align-baseline content-start">
        <div class="full-width flex justify-center align-middle" style="flex: 0">
          <q-chip size="sm" label="Hello" outline/>
          <q-space/>
          <q-icon size="sm" name="list"/>
        </div>
        <div class="q-pt-xs row q-gutter-x-lg" style="flex: 1">
          <div class="col flex flex-row justify-between">
            <div class="text-h6 ellipsis-2-lines full-width"
                 style="height: calc(1em * 2 * 1.5);line-height: 1.5em">{{ item.name }}
            </div>
            <div class="flex-1">
              <div class="ellipsis-3-lines" style="height: calc(1em * 3 * 1.5);flex: 1">
                {{ item.description }}
              </div>
            </div>
            <div class="q-mt-sm">
              <q-avatar size="sm" class="q-mr-xs">
                <q-img v-if="item.avatar" :src="item.avatar"/>
              </q-avatar>
              <span class="text-grey-7">{{ item.author }}</span>
            </div>
          </div>
          <div v-if="item.image" class="col-3 flex items-center justify-center">
            <q-img :alt="item.name" :src="item.image"/>
          </div>
        </div>
      </q-card-section>
    </q-card>
  </div>
  <div class="full-width flex q-mt-lg text-center justify-center items-center">
    <q-pagination
      :model-value="form.pageNow || 1"
      :max="data.totalPage"
      input
      @update:model-value="handleSearch"
    />
  </div>
</template>

<style scoped lang="scss">
.data-list {
  display: grid;
  grid-template-columns: repeat(3, calc(33.33% - 14px));
  grid-auto-rows: auto;
  grid-gap: 24px;
}
</style>
