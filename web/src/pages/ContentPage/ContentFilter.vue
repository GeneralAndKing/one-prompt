<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import { ModelCategoryFilter, ModelTab } from '@/api/Prompt'

interface Props {
  currentTab: ModelTab | null
}

const props = defineProps<Props>()
const filters = ref<string[]>([])

const categoryFilters = computed<ModelCategoryFilter[]>(() => {
  if (!props.currentTab) {
    return []
  }
  return props.currentTab?.categories
})

const resetFilters = () => {
  filters.value = []
}

watch(() => props.currentTab, () => {
  resetFilters()
})

defineExpose({
  resetFilters,
  filters
})

</script>

<template>
  <q-list padding>
    <div class="q-mt-sm">
      <q-item v-for="(item) in categoryFilters" :key="`checkbox-${item.id}`" tag="label" dense v-ripple>
        <q-item-section side top>
          <q-checkbox dense :val="item.id" v-model="filters"/>
        </q-item-section>
        <q-item-section>
          <q-item-label>{{ item.name }}</q-item-label>
        </q-item-section>
      </q-item>
    </div>
  </q-list>
</template>

<style scoped lang="scss">

</style>
