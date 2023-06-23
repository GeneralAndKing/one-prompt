<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { ref } from 'vue'

const search = ref('')

const { t } = useI18n({ useScope: 'global' })
</script>

<template>
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
</template>

<style scoped lang="scss">
.home {
  margin-top: 5rem;
}
</style>
