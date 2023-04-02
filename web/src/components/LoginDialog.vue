<template>
  <q-dialog
    :model-value="props.modelValue"
    @update:model-value="updateValue"
    persistent
    transition-show="jump-up"
    transition-hide="jump-down">
    <q-card :dark="false" class="q-py-lg text-black login text-center">
      <div class="title">微信登录</div>
      <div class="sub-title">使用微信扫码登录</div>
      <q-img class="code"
             :src="code?.base64Code"/>
      <div class="tip">登录即代表您同意用户协议</div>
      <q-btn @click="updateValue(false)" class="close" outline round size="10px" color="grey-6" icon="close"/>
    </q-card>
  </q-dialog>
</template>

<script lang="ts" setup>

import { ref, watch } from 'vue'
import { authCode } from 'src/api/Auth'
import { AuthCode } from 'src/model/Auth'

interface LoginProps {
  modelValue: boolean
}

const props = defineProps<LoginProps>()
const emit = defineEmits(['update:modelValue'])

const code = ref<AuthCode | null>(null)
const updateValue = (value: boolean) => emit('update:modelValue', value)

watch(() => props.modelValue, async (val: boolean) => {
  console.log(props.modelValue, val)
  if (val) {
    code.value = await authCode()
    console.log(code.value.base64Code)
  }
})

</script>

<style lang="scss" scoped>
.login {
  width: 600px;
  border-radius: 24px;

  .title {
    font-size: 28px;
    font-weight: 500;
    line-height: 40px;
  }

  .sub-title {
    font-size: 20px;
    margin-top: 8px;
  }

  .code {
    margin-top: 50px;
    width: 280px;
    height: 280px;
  }

  .tip {
    margin-top: 75px;
    font-size: 18px;
    color: #999999;
  }

  .close {
    position: absolute;
    right: 30px;
    top: 30px;
  }
}
</style>
