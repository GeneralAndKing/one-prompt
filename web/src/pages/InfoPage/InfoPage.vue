<template>
  <div id="info">
    <div class="text-white text-h5">
      <q-breadcrumbs active-color="white" class="text-white text-h5" gutter="sm">
        <q-breadcrumbs-el label="Home"/>
        <q-breadcrumbs-el label="Components"/>
        <q-breadcrumbs-el label="Portrait of a girl in officer's ceremonial tunic"/>
      </q-breadcrumbs>
    </div>
    <q-separator class="q-mt-md" color="gray"/>
    <div class="row no-wrap q-mt-lg justify-between">
      <q-img
        class="info-img q-mr-lg"
        :src="`https://api.r10086.com/img-api.php?type=%E5%8A%A8%E6%BC%AB%E7%BB%BC%E5%90%881&z${new Date().toISOString()}`"/>
      <div>
        <div class="info-img-content q-gutter-lg justify-between">
          <q-img
            class="info-img-sub"
            v-for="item in [1,2,3,4,5,6]"
            :key="item"
            style="border-radius: 16px"
            :src="`https://api.r10086.com/img-api.php?type=%E5%8A%A8%E6%BC%AB%E7%BB%BC%E5%90%881&z${new Date().toISOString() + item}`"/>
        </div>
        <q-card class="bg-tip q-pa-lg q-mt-lg">
          (masterpiece:1.0), (best quality:1.4), (ultra highres:1.2), (photorealistic:1.4), (8k, RAW photo:1.2), (soft focus:1.4), 1 woman, (sharp focus:1.4), (korean:1.2), (american:1.1), detailed beautiful face, black hair, (detailed white dress:1.4), beautiful white shiny humid skin, smiling ,Full Length Shot(FLS)
          <div class="q-mt-md">
            <q-avatar style="width: 32px;height: 32px;border-radius: 24px" rounded>
              <img alt="avatar" src="https://api.r10086.com/img-api.php?type=%E5%8A%A8%E6%BC%AB%E7%BB%BC%E5%90%881" />
            </q-avatar>
            <span class="q-ml-sm">Roddy Meister</span>
          </div>
        </q-card>
        <q-card class="bg-tip q-pa-lg q-mt-lg">
          <div class="row q-gutter-x-md">
            <q-btn class="info-action" outline rounded color="white" icon="favorite_border" label="喜欢" />
            <q-btn class="info-action" outline rounded color="white" icon="share" label="分享" />
            <q-btn class="info-action" rounded color="primary" icon="share" label="复制" />
          </div>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

</script>

<style lang="scss" scoped>
#info {
  padding: 60px 0 0;
  max-width: 1440px;
  margin: 0 auto;

  .info-img {
    flex-shrink: 0;
    width: 829px;
    height: 829px;
    border-radius: 16px;
  }

  .bg-tip {
    background: rgba(169, 197, 255, 0.2);
  }

  .info-img-content {
    flex: 33.33%;

    .info-img-sub {
      width: 170px;
      height: 170px;
    }
  }
  .info-action {
    font-size: 10px;
    border-radius: 8px;
  }
}
</style>
