<script setup lang="ts">
import { QVueGlobals, useQuasar } from 'quasar'
import { useI18n } from 'vue-i18n'
import { languages } from '@/i18n'

const $q: QVueGlobals = useQuasar()

const { t, locale } = useI18n({
  useScope: 'global'
})
</script>

<template>
  <q-layout>
    <q-header class="header" elevated>
      <q-toolbar class="toolbar">
        <q-btn class="q-mr-md" size="1.25rem" flat label="ONE PROMPT"/>
        <!--          <q-input style="width: 40%" v-model="search" :placeholder="t('page.chat.search')" outlined standout borderless-->
        <!--                   dense>-->
        <!--            <template v-slot:prepend>-->
        <!--              <q-icon name="search"/>-->
        <!--            </template>-->
        <!--          </q-input>-->
        <q-space/>
        <q-btn @click="$q.dark.toggle" flat round :icon="$q.dark.mode ? 'brightness_4' : 'brightness_high'">
          <q-tooltip :delay="1000" :hide-delay="2000">
            {{ t('tooltip.changeTheme') }}
          </q-tooltip>
        </q-btn>
        <q-btn class="q-mr-md" flat round icon="language">
          <q-tooltip :delay="1000" :hide-delay="2000">
            {{ t('tooltip.changeLanguage') }}
          </q-tooltip>
          <q-menu fit anchor="bottom start" self="top left">
            <q-item v-for="language in languages" :key="language.value" @click="locale = language.value" clickable
                    v-close-popup>
              <q-item-section>{{ language.label }}</q-item-section>
            </q-item>
          </q-menu>
        </q-btn>
        <q-btn class="q-mr-md" color="primary" :label="t('action.signIn')"/>
        <q-btn color="secondary" :label="t('action.signUp')"/>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <q-page padding>
        <router-view />
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<style scoped lang="scss">
.header {
  padding: 0.5rem 0;

  .toolbar {
    max-width: 1440px;
    margin: 0 auto;
  }
}

.body--light {
  .header {
    background-color: white;
    color: black;
  }
}

.body--dark {
  .header {
    background-color: $dark-page;
    color: white;
  }
}
</style>
