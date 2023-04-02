<template>
 <div class="home-ai flex full-width justify-center">
   <template v-for="model in data" :key="`channel-${model.id}`">
     <q-card flat bordered class="home-ai-item">
       <q-card-section horizontal>
         <q-img
           class="home-ai-item-img"
           :src="model.logo"
         />
         <q-card-section class="channel-info row q-py-none column justify-between">
           <div class="channel-content">
             <div class="channel-name">{{ model.name }}</div>
             <div class="channel-description">{{ model.description }}</div>
           </div>
           <div class="channel-action row justify-between items-center">
             <q-btn style="width: 145px" rounded color="primary">立即查看</q-btn>
             <div>学习</div>
           </div>
         </q-card-section>
       </q-card-section>
     </q-card>
   </template>
 </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { Model } from 'src/model/Model'
import { modelList } from 'src/api/Model'

const data = ref<Model[]>([])

onMounted(async () => {
  const value = await modelList()
  data.value = value.slice(0, value.length / 2 * 2 - 1)
  console.log(value)
})
</script>

<style lang="scss" scoped>

.home-ai {
  margin: 100px 0;
  gap: 40px;

  .home-ai-item {
    max-width: 700px;
    padding: 24px;
    background-color: $dark-page;

    .home-ai-item-img {
      min-width: 349px;
      height: 322px;
    }

    .channel-info {
      .channel-name {
        font-size: 44px;
        font-weight: 500;
      }

      .channel-description {
        margin-top: 40px;
        font-size: 14px;
      }
    }
  }
}
</style>
