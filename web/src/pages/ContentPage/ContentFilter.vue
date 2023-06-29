<script setup lang="ts">
import { computed, reactive, watch } from 'vue'
import { ChannelFilter, ChannelFilterType, ChannelTab } from '@/api/Channel'
interface Props {
  currentTab: ChannelTab | null
}
const props = defineProps<Props>()
const filters = reactive<{ [key: string]: any }>({})

interface ChannelTabFilters {
  select: ChannelFilter[],
  checkbox: ChannelFilter[],
  toggle: ChannelFilter[]
}
const currentFilters = computed<ChannelTabFilters>(() => {
  if (!props.currentTab) {
    return {
      select: [],
      checkbox: [],
      toggle: []
    }
  }
  return {
    select: props.currentTab?.filters.filter(item => item.type === ChannelFilterType.SELECT) || [],
    checkbox: props.currentTab?.filters.filter(item => item.type === ChannelFilterType.CHECKBOX) || [],
    toggle: props.currentTab?.filters.filter(item => item.type === ChannelFilterType.TOGGLE) || []
  }
})
const resetFilters = () => {
  for (const filter of currentFilters.value.checkbox) {
    if (!filter.options) {
      continue
    }
    for (const option of filter.options) {
      filters[option.name] = false
    }
  }
  for (const filter of currentFilters.value.toggle) {
    filters[filter.name] = false
  }
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
    <template v-for="item in currentFilters.toggle" :key="`toggle-${item.id}`">
      <q-item>
        <q-item-section>
          <q-item-label>{{ item.name }}</q-item-label>
        </q-item-section>
        <q-item-section side>
          <q-toggle
            dense
            :true-value="true"
            :false-value="false"
            v-model="filters[item.name]"
          />
        </q-item-section>
      </q-item>
    </template>
    <template v-for="(item, index) in currentFilters.select" :key="`select-${item.id}`">
      <q-separator v-if="index === 0 && currentFilters.toggle.length > 0" spaced/>
      <q-item>
        <q-item-section>
          <q-item-label>{{ item.name }}</q-item-label>
        </q-item-section>
        <q-item-section side>
          <q-select
            v-model="filters[item.name]"
            :options="item.options"
            hide-dropdown-icon
            option-label="name"
            option-value="value"
            dense
            :style="filters[item.name] || { width: '40px' }"
          >
            <template v-slot:selected>
              <div class="full-width text-center">
                {{ filters[item.name]?.name }}
              </div>
            </template>
          </q-select>
        </q-item-section>
      </q-item>
    </template>
    <template v-for="(item, index) in currentFilters.checkbox" :key="`checkbox-${item.id}`">
      <q-separator v-if="index === 0 && currentFilters.select.length > 0" spaced/>
      <q-expansion-item
        :model-value="index === 0"
        :label="item.name"
      >
        <div class="q-mt-sm">
          <q-item v-for="option in item.options" :key="option.id" tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox :true-value="true" :false-value="false" dense v-model="filters[option.name]"/>
            </q-item-section>
            <q-item-section>
              <q-item-label>{{ option.name }}</q-item-label>
            </q-item-section>
          </q-item>
        </div>
      </q-expansion-item>
      <q-separator v-if="index !== currentFilters.checkbox.length - 1" spaced/>
    </template>
  </q-list>
</template>

<style scoped lang="scss">

</style>
