<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, reactive, ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { ChannelSearchParam, search, SearchResponse } from '@/api/Channel'
import useBoolean from '@/hooks/useBoolean'

const { t } = useI18n({ useScope: 'global' })
const route = useRoute()
const form = reactive<ChannelSearchParam>({
  engine: 'chatGPT',
  keywords: '',
  trending: [],
  pageNow: 1,
  pageSize: 10,
  filters: {}
})
const view = ref('one')

onMounted(() => {
  const engine = route.params.engine
  console.log(engine)
  handleSearch()
})

const data = ref<SearchResponse[]>([])
const { state: loading, setTrue: start, setFalse: finish } = useBoolean()

const handleSearch = async () => {
  start()
  try {
    data.value = await search(form)
  } finally {
    finish()
  }
}
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
      <q-tab name="chatGPT" label="chatGPT" />
      <q-tab name="Mid" label="Mid" />
      <q-tab name="文心一言" label="文心一言" />
    </q-tabs>
    <q-separator />
    <div class="form q-mt-md row q-gutter-x-lg">
      <q-btn outline class="col-2">
        <q-icon name="filter_list" />
        Show Filters
      </q-btn>
      <q-input class="col-7"
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

      <q-btn-dropdown outline color="col-1" label="Trending">
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
          <q-icon name="window" />
        </template>
        <template v-slot:two>
          <q-icon name="calendar_view_week" />
        </template>
      </q-btn-toggle>
    </div>
    <div class="row flex flex-nowrap">
      <div class="filter col-3">
        <q-list padding>
          <q-item>
            <q-item-section>
              <q-item-label>ChatGPT Version</q-item-label>
            </q-item-section>
            <q-item-section side>
              <q-select
                model-value="3.5"
                :options="['3.5', '4.0']"
                hide-dropdown-icon
                dense
                style="width: 40px"
              >
                <template v-slot:selected>
                  <div class="full-width text-center">
                    3.5
                  </div>
                </template>
              </q-select>
            </q-item-section>
          </q-item>
          <q-separator spaced />
          <q-item-label header>Category</q-item-label>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>
            <q-item-section>
              <q-item-label>Computer Science</q-item-label>
            </q-item-section>
          </q-item>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>
            <q-item-section>
              <q-item-label>Humanities</q-item-label>
            </q-item-section>
          </q-item>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>
            <q-item-section>
              <q-item-label>Role Assignment</q-item-label>
            </q-item-section>
          </q-item>
          <q-separator spaced />
          <q-item-label header>Use Method</q-item-label>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>

            <q-item-section>
              <q-item-label>One Prompt</q-item-label>
            </q-item-section>
          </q-item>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>

            <q-item-section>
              <q-item-label>Model Training</q-item-label>
            </q-item-section>
          </q-item>
          <q-item tag="label" dense v-ripple>
            <q-item-section side top>
              <q-checkbox />
            </q-item-section>

            <q-item-section>
              <q-item-label>Notifications</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
      </div>
      <div class="data-list q-mt-lg col">
        <q-card v-for="item in data" :key="item.id" class="col-6">
          <q-card-section>
            <div class="full-width flex justify-center align-middle">
              <q-chip size="sm" label="Hello" outline />
              <q-space />
              <q-icon size="sm" name="list" />
            </div>
            <div class="q-pt-xs row q-gutter-x-lg">
              <div class="col flex justify-between">
                <div class="text-h6">{{ item.name }}</div>
                <div class="ellipsis-3-lines" style="height: calc(1em * 3 * 1.5)">
                  {{ item.description }}
                </div>
                <div>
                  <q-avatar size="sm" class="q-mr-xs">
                    <q-img v-if="item.avatar" :src="item.avatar" />
                  </q-avatar>
                  <span class="text-grey-7">
                    {{ item.author }}
                  </span>
                </div>
              </div>
              <div class="col-3">
                <q-img v-if="item.image" :src="item.image" />
              </div>
            </div>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<style scoped lang="scss">
.data-list {
  display: grid;
  grid-template-columns: repeat(2, calc( 50% - 14px));
  grid-gap: 24px;
}
</style>
