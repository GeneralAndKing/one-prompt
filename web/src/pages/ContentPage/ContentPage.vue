<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, onMounted, reactive, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'
import {
  SearchParam,
  ModelTab,
  getAllModel,
  search,
  SearchResponse
} from '@/api/Prompt'
import useBoolean from '@/hooks/useBoolean'
import ContentFilter from '@/pages/ContentPage/ContentFilter.vue'
import ContentList from '@/pages/ContentPage/ContentList.vue'

const { t } = useI18n({ useScope: 'global' })
const route = useRoute()
const contentFilter = ref<InstanceType<typeof ContentFilter>>()
const form = reactive<SearchParam>({
  modelId: null,
  keywords: '',
  categories: [],
  pageNow: 1,
  pageSize: 10,
  sort: []
})
const view = ref('one')
const data = ref<SearchResponse>({
  list: [],
  pageNow: 1,
  pageSize: 10,
  total: 0,
  totalPage: 0
})
const channelTabs = ref<ModelTab[]>([])
const { state: showFilters, toggle: toggleFilters } = useBoolean(false)
const { state: loading, setTrue: start, setFalse: finish } = useBoolean()

onMounted(async () => {
  channelTabs.value = await getAllModel()
  const modelIdString = route.params.modelId
  if (modelIdString === '' || modelIdString.length === 0) {
    form.modelId = channelTabs.value[0].id
  } else {
    let modelId = channelTabs.value[0].id
    if (typeof modelIdString === 'string') {
      modelId = parseInt(modelIdString)
    } else {
      modelId = parseInt(modelIdString[0])
    }
    const find = channelTabs.value.map(item => item.id).find(item => item === modelId)
    form.modelId = find ? modelId : channelTabs.value[0].id
  }
  contentFilter.value?.resetFilters()
  await handleSearch()
})

const currentTab = computed<ModelTab | null>(() => {
  if (!channelTabs.value || !form.modelId) {
    return null
  }
  return channelTabs.value.find(item => item.id === form.modelId) || null
})

const handleSearch = async (pageNow = 1) => {
  form.pageNow = pageNow
  form.categories = contentFilter.value.filters
  start()
  try {
    data.value = await search(form)
  } finally {
    finish()
  }
}
watch(() => form.modelId, async () => {
  form.keywords = ''
  form.sort = []
  form.pageNow = 1
  form.pageSize = 1
  contentFilter.value?.resetFilters()
  await handleSearch()
})
</script>

<template>
  <q-page class="content">
    <q-tabs
      v-model="form.modelId"
      class="text-grey"
      active-color="primary"
      indicator-color="primary"
      narrow-indicator
      align="left"
    >
      <q-tab v-for="tab in channelTabs" :key="`tab-${tab.id}`" :name="tab.id" :label="tab.name"/>
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
               @keyup.enter="handleSearch(1)"
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
        <content-filter ref="contentFilter" :current-tab="currentTab" />
      </div>
      <div class="q-mt-lg q-ml-lg col">
        <content-list :data="data" :form="form" :loading="loading" @handle-search="handleSearch" />
      </div>
    </div>
  </q-page>
</template>

<style scoped lang="scss">

</style>
