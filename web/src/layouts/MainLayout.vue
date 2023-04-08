<template>
  <q-layout class="container" view="lHh lpR fFf">

    <q-header bordered class="bg-black text-white">
      <q-toolbar class="row justify-between">
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-input filled style="width: 500px" bg-color="bg-gray-800" standout=" text-white" color="bg-gray-800"  placeholder="Search">
          <template v-slot:prepend>
            <q-icon name="search" />
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
            icon="mail"
            expand-icon-class="menu-icon"
            label="Inbox">
            <div style="margin-left: 40px; border-left: 1px solid #999999">
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>全部</q-item-section>
                <q-checkbox size="sm" :model-value="false" />
              </q-item>
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>动物</q-item-section>
                <q-checkbox size="sm" :model-value="true" />
              </q-item>
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>哈哈哈</q-item-section>
                <q-checkbox size="sm" :model-value="true" />
              </q-item>
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>啊啥啥啥</q-item-section>
                <q-checkbox size="sm" :model-value="false" />
              </q-item>
            </div>
          </q-expansion-item>
          <q-expansion-item
            icon="mail"
            label="Inbox">
            <div style="margin-left: 40px; border-left: 1px solid #999999">
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>Icon as avatar</q-item-section>
                <q-checkbox  />
              </q-item>
            </div>
          </q-expansion-item>
          <q-expansion-item
            icon="mail"
            label="Inbox">
            <div style="margin-left: 40px; border-left: 1px solid #999999">
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>Icon as avatar</q-item-section>
                <q-checkbox  />
              </q-item>
            </div>
          </q-expansion-item>
          <q-expansion-item
            icon="mail"
            label="Inbox">
            <div style="margin-left: 40px; border-left: 1px solid #999999">
              <q-item style="height: 20px" dense clickable v-ripple>
                <q-item-section>Icon as avatar</q-item-section>
                <q-checkbox  />
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
    <login-dialog v-model="showLogin" />
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import LoginDialog from 'components/LoginDialog.vue'
import { useApplicationStore } from '@/stores/useApplicationStore'
const applicationStore = useApplicationStore()
const showLogin = ref(false)

const leftDrawerOpen = ref(false)

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value
}

</script>

<style  lang="scss">
.container {
  .header {
    height: 66px;
  }
  .q-item__section--side {
  }
}
</style>
