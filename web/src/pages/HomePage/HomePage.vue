<template>
  <div id="home">
    <div class="home-nav absolute full-width row justify-between items-center q-px-lg-xl">
      <img class="home-nav-logo q-ml-xl" alt="logo" src="https://resources.echocow.cn/prompt/logo-line.png"/>
      <div class="home-nav-action xs-hide q-mr-sm-xl lt-q-mr-none">
        <q-btn class="home-nav-button" style="margin-right: 24px" flat rounded color="white" label="注册"/>
        <q-btn class="home-nav-button" unelevated rounded color="primary" @click="showLogin = true" label="登陆"/>
      </div>
    </div>
    <div class="home-main flex justify-center column">
      <img class="home-main-logo" alt="logo" src="https://resources.echocow.cn/prompt/logo-line.png"/>
      <div class="home-main-content">
        分享你的 Prompt<br/>
        更好的从 AI 那里获取到更多价值
      </div>
      <div class="home-main-action flex">
        <q-btn class="home-action-button q-mr-xl" color="primary" label="查看"/>
        <q-btn outline class="home-action-button" color="white" label="分享"/>
      </div>
    </div>
    <home-ai />
    <home-category />
    <div class="home-our row justify-between items-center">
      <q-img height="72px" width="384px" fit="contain" src="https://resources.echocow.cn/prompt/logo-line.png" />
      <div>
        二维码
      </div>
    </div>
    <login-dialog v-model="showLogin" />
  </div>
</template>

<script setup lang="ts">
import HomeAi from 'pages/HomePage/HomeAi.vue'
import LoginDialog from 'components/LoginDialog.vue'
import { ref } from 'vue'
import HomeCategory from 'pages/HomePage/HomeCategory.vue'

const showLogin = ref(false)

</script>

<style scoped lang="scss">
#home {
  .home-nav {
    margin-top: 20px;

    .home-nav-logo {
      width: 125px;
      height: 26px;
    }

    .home-nav-button {
      width: 102px;
    }
  }

  .home-main {
    height: 100vh;
    background-image: url("https://resources.echocow.cn/prompt/banner.png");
    background-repeat: no-repeat;
    background-position: center center;
    background-size: cover;
    padding-left: 240px;

    .home-main-logo {
      width: 384px;
      height: 79px;
    }

    .home-main-content {
      margin-top: 71px;
      font-size: 42px;
      color: #FFFFFF;
      line-height: 57px;
      font-weight: 1000;
    }

    .home-main-action {
      margin-top: 70px;;

      .home-action-button {
        width: 170px;
        height: 50px;
        font-size: 18px;
        border-radius: 8px;
      }
    }

    @media (max-width: $breakpoint-md-max) {
      padding-left: 200px;
    }
    @media (max-width: $breakpoint-sm-max) {
      padding-left: 80px;
      .home-main-logo {
        width: 284px;
        height: 59px;
      }

      .home-main-content {
        margin-top: 60px;
        font-size: 35px;
      }

      .home-main-action {
        margin-top: 50px;;
      }
    }
    @media (max-width: $breakpoint-xs-max) {
      padding-left: 10px;
      .home-main-logo {
        display: none;
      }

      .home-main-content {
        font-size: 35px;
        text-align: center;
      }

      .home-main-action {
        margin-top: 50px;
        justify-content: center;

        .home-action-button {
          width: 100px;
        }
      }
    }

  }

  .home-our {
    margin: 40px 240px 80px;
  }
}
</style>
