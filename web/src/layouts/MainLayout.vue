<template>
  <q-layout class="container" view="lHh lpR fFf">

    <q-header bordered class="bg-black text-white">
      <q-toolbar class="row justify-between">
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer"/>
        <q-input filled style="width: 500px" bg-color="bg-gray-800" standout=" text-white" color="bg-gray-800"
                 placeholder="Search">
          <template v-slot:prepend>
            <q-icon name="search"/>
          </template>
        </q-input>
        <div class="flex q-gutter-md">
          <template v-if="!applicationStore.isLogin">
            <q-btn class="home-nav-button" style="margin-right: 24px" flat rounded color="white" label="注册"/>
            <q-btn class="home-nav-button" unelevated rounded color="primary" @click="showLogin = true" label="登陆"/>
          </template>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer :width="210" class="bg-black" show-if-above v-model="leftDrawerOpen" side="left">
      <q-scroll-area style="height: calc(100% - 150px);">
        <q-img class="q-mx-auto flex q-mt-lg" width="125px" src="https://resources.echocow.cn/prompt/logo-line.png"/>
        <q-list style="margin-top: 20px" dense dark>
          <q-expansion-item
            v-for="(item, menuIndex) in menu"
            :key="`expansion-${item.id}`"
            icon="mail"
            :label="item.name">
            <template v-slot:header>
              <q-item-section avatar>
                <q-icon color="purple" name="signal_wifi_off" />
              </q-item-section>
              <q-item-section>{{item.name}}</q-item-section>
            </template>
            <div v-for="(category, categoryIndex) in item.categories" :key="`category-${category.id}`"
                 style="margin-left: 40px; border-left: 1px solid #999999">
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>{{ category?.name }}</q-item-section>
                <q-checkbox :model-value="category?.value"
                            @update:modelValue="handleCheck($event,menuIndex, categoryIndex)"/>
              </q-item>
            </div>
          </q-expansion-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <!--    <q-drawer show-if-above v-model="rightDrawerOpen" side="right" bordered>-->
    <!-- drawer content -->
    <!--    </q-drawer>-->

    <q-page-container>
      <router-view/>
    </q-page-container>
    <login-dialog v-model="showLogin"/>
  </q-layout>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import LoginDialog from 'components/LoginDialog.vue'
import { useApplicationStore } from '@/stores/useApplicationStore'
import { dashboardMenu } from '@/api/Dashboard'
import { Navigation } from '@/model/Dashboard'

const applicationStore = useApplicationStore()
const showLogin = ref(false)

const leftDrawerOpen = ref(false)
const menu = ref<Navigation[]>([])

onMounted(async () => {
  const list = await dashboardMenu()
  menu.value = list.map(item => {
    item.categories?.unshift({
      id: 0,
      sort: 0,
      name: '全部'
    })
    item.categories = item.categories?.map((category) => {
      category.value = false
      return category
    })
    return item
  })
})

const handleCheck = (val: boolean, menuIndex: number, categoryIndex: number) => {
  // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
  const categories = menu.value[menuIndex].categories!
  const category = categories[categoryIndex]
  category.value = val
  // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
  menu.value[menuIndex].categories![categoryIndex] = category
}
const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value
}

</script>

<style lang="scss">
.container {
  .header {
    height: 66px;
  }

  .q-item__section--side {
  }
}
</style>
