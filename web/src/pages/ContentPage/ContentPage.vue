<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, onMounted, reactive, ref, watch, watchEffect } from 'vue'
import { useI18n } from 'vue-i18n'
import {
  ChannelFilter,
  ChannelFilterType,
  ChannelSearchParam,
  ChannelTab,
  getAllChannel,
  search,
  SearchResponse
} from '@/api/Channel'
import useBoolean from '@/hooks/useBoolean'

const { t } = useI18n({ useScope: 'global' })
const route = useRoute()
const form = reactive<ChannelSearchParam>({
  engine: '',
  keywords: '',
  trending: [],
  pageNow: 1,
  pageSize: 10,
  filters: {}
})
const view = ref('one')
const data = ref<SearchResponse>({
  list: [],
  pageNow: 1,
  pageSize: 10,
  total: 0,
  totalPage: 0
})
const channelTabs = ref<ChannelTab[]>([])
const { state: showFilters, toggle: toggleFilters } = useBoolean(false)
const { state: loading, setTrue: start, setFalse: finish } = useBoolean()

const resetFilters = () => {
  if (form.filters === null) {
    form.filters = {}
  }
  for (const channel of channelTabs.value) {
    for (const filter of channel.filters.filter(item => item.type === ChannelFilterType.CHECKBOX)) {
      if (!filter.options) {
        continue
      }
      for (const option of filter.options) {
        form.filters[option.name] = false
      }
    }
    for (const filter of channel.filters.filter(item => item.type === ChannelFilterType.TOGGLE)) {
      form.filters[filter.name] = false
    }
  }
}

onMounted(async () => {
  channelTabs.value = await getAllChannel()
  const engine = route.params.engine
  if (engine === '') {
    form.engine = channelTabs.value[0].name
  } else {
    const find = channelTabs.value.map(item => item.name).find(item => item === engine)
    if ((find && find.length === 0) || !find) {
      form.engine = channelTabs.value[0].name
    } else {
      form.engine = engine[0]
    }
  }
  await resetFilters()
  await handleSearch()
})

const currentTab = computed<ChannelTab | null>(() => {
  if (!channelTabs.value || !form.engine) {
    return null
  }
  return channelTabs.value.find(item => item.name === form.engine) || null
})

interface ChannelTabFilters {
  select: ChannelFilter[],
  checkbox: ChannelFilter[],
  toggle: ChannelFilter[]
}

const currentFilters = computed<ChannelTabFilters>(() => {
  if (!currentTab.value) {
    return {
      select: [],
      checkbox: [],
      toggle: []
    }
  }
  return {
    select: currentTab.value?.filters.filter(item => item.type === ChannelFilterType.SELECT) || [],
    checkbox: currentTab.value?.filters.filter(item => item.type === ChannelFilterType.CHECKBOX) || [],
    toggle: currentTab.value?.filters.filter(item => item.type === ChannelFilterType.TOGGLE) || []
  }
})
const handleSearch = async (pageName = 1) => {
  form.pageNow = pageName
  start()
  try {
    data.value = await search(form)
  } finally {
    finish()
  }
}
watch(() => form.engine, async () => {
  form.keywords = ''
  form.trending = []
  form.pageNow = 1
  form.pageSize = 1
  await resetFilters()
  await handleSearch()
})
</script>

<template>
  <q-page class="content">
    <q-tabs
      v-model="form.engine"
      class="text-grey"
      active-color="primary"
      indicator-color="primary"
      narrow-indicator
      align="left"
    >
      <q-tab v-for="tab in channelTabs" :key="tab.id" :name="tab.name" :label="tab.name"/>
    </q-tabs>
    <q-separator/>
    <div class="form q-mt-md row q-gutter-x-lg">
      <q-btn outline class="col-2" @click="toggleFilters">
        <q-icon name="filter_list"/>
        Show Filters
      </q-btn>
      <q-input class="col"
               v-model="form.keywords"
               :placeholder="t('page.chat.search')"
               outlined
               standout
               borderless
               :loading="loading"
               :readonly="loading"
               @keyup.enter="handleSearch"
               dense>
        <template v-slot:prepend>
          <q-icon name="search"/>
        </template>
      </q-input>

      <q-btn-dropdown outline class="col-1" label="Trending">
        <q-list>
          <q-item clickable v-close-popup>
            <q-item-section>
              <q-item-label>Photos</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-close-popup>
            <q-item-section>
              <q-item-label>Videos</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-close-popup>
            <q-item-section>
              <q-item-label>Articles</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
      </q-btn-dropdown>

      <q-btn-toggle
        v-model="view"
        push
        :options="[
          {value: 'one', slot: 'one'},
          {value: 'two', slot: 'two'}
        ]"
      >
        <template v-slot:one>
          <q-icon name="window"/>
        </template>
        <template v-slot:two>
          <q-icon name="calendar_view_week"/>
        </template>
      </q-btn-toggle>
    </div>
    <div class="row flex flex-nowrap">
      <div :class="showFilters ? '': 'hidden'" class="filter col-2">
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
                  v-model="form.filters[item.name]"
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
                  v-model="form.filters[item.name]"
                  :options="item.options"
                  hide-dropdown-icon
                  option-label="name"
                  option-value="value"
                  dense
                  :style="form.filters[item.name] || { width: '40px' }"
                >
                  <template v-slot:selected>
                    <div class="full-width text-center">
                      {{ form.filters[item.name]?.name }}
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
                    <q-checkbox :true-value="true" :false-value="false" dense v-model="form.filters[option.name]"/>
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
      </div>
      <div class="q-mt-lg q-ml-lg col">
        <div v-show="!loading" class="data-list">
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
            v-model="form.pageNow"
            :max="data.totalPage"
            input
            @update:model-value="handleSearch"
          />
        </div>
      </div>
    </div>
  </q-page>
</template>

<style scoped lang="scss">
.data-list {
  display: grid;
  grid-template-columns: repeat(3, calc(33.33% - 14px));
  grid-auto-rows: auto;
  grid-gap: 24px;
}
</style>
