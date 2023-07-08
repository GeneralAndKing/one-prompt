<script setup lang="ts">
import { computed, h, ref, watch } from 'vue'
import { ModelCategoryFilter, ModelTab } from '@/api/Prompt'
import { QCheckbox, QItem, QItemLabel, QItemSection, QList, useQuasar } from 'quasar'

interface Props {
  currentTab: ModelTab | null,
  showFilters: boolean
}

const props = defineProps<Props>()
const emit = defineEmits(['toggleFilter', 'search'])
const filters = ref<number[]>([])
const $q = useQuasar()

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

const filterNodes = () =>
  h(QList, { padding: true }, () => h('div', {
    class: 'q-mt-sm'
  }, categoryFilters.value.map(item => h(QItem, {
    key: `checkbox-${item.id}`,
    tag: 'label',
    dense: true
  }, () => [
    h(QItemSection, { side: true, top: true },
      () => h(QCheckbox, {
        dense: true,
        val: item.id,
        modelValue: filters.value,
        'onUpdate:modelValue': (val: number[]) => {
          filters.value = val
        }
      })),
    h(QItemSection, {},
      () => h(QItemLabel, () => item.name))
  ]))))

const handleSearch = () => {
  emit('search')
  emit('toggleFilter')
}

</script>

<template>
  <div class="gt-sm col-2" :class="{hidden: showFilters}" style="flex: 15rem">
    <filter-nodes />
  </div>
  <div v-if="$q.screen.lt.md" >
    <q-dialog :model-value="showFilters" maximized position="bottom">
      <q-card style="height: 100vh" class="flex column">
        <q-card-section class="flex items-center">
          <div class="text-h5 text-bold">Filters</div>
          <q-space/>
          <q-icon name="close" class="text-h6 cursor-pointer" @click="() => emit('toggleFilter')"/>
        </q-card-section>
        <q-card-section style="flex: 1">
          <filter-nodes />
        </q-card-section>
        <q-card-actions>
          <q-btn color="primary" class="full-width" @click="handleSearch">确认</q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>
