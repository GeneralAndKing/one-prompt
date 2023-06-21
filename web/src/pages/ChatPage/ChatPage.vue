<script setup lang="ts">

import { ref } from 'vue'
import { QVueGlobals, useQuasar } from 'quasar'
import { useI18n } from 'vue-i18n'
import { languages } from '@/i18n'

const search = ref('')
const $q: QVueGlobals = useQuasar()

const { t, locale } = useI18n({
  useScope: 'global'
})
</script>

<template>
  <div class="chat">
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
          <div class="home flex column justify-center items-center">
            <div class="text-center text-h3 text-weight-medium"
                 style="line-height: 160%;"
                 v-html="t('page.home.title')"
            />
            <div class="text-h6 text-weight-regular q-my-md"
                 v-html="t('page.home.subtitle')"
            />
            <div class="flex q-gutter-x-sm q-mb-lg">
              <q-chip size="1rem" clickable outline square icon="alarm" label="Set alarm"/>
              <q-chip size="1rem" clickable outline square icon="directions">
                Get directions
              </q-chip>
            </div>
            <q-input style="width: 28rem" v-model="search" :placeholder="t('page.chat.search')" outlined standout
                     borderless dense>
              <template v-slot:prepend>
                <q-icon name="search"/>
              </template>
            </q-input>
            <div class="flex q-gutter-x-lg q-mt-lg">
              <q-btn outline style="width: 8rem" color="primary" :label="t('page.home.try')"/>
              <q-btn style="width: 8rem" color="secondary" :label="t('page.home.share')"/>
            </div>
          </div>
        </q-page>
      </q-page-container>
    </q-layout>
  </div>
</template>

<style scoped lang="scss">
.chat {
  .header {
    padding: 0.5rem 0;

    .toolbar {
      max-width: 1440px;
      margin: 0 auto;
    }
  }
  .home {
    margin-top: 5rem;
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
