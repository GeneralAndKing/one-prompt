<template>
  <div id="home">
    <div class="home-nav absolute full-width row justify-between items-center q-px-lg-xl">
      <img class="home-nav-logo q-ml-xl" alt="logo" src="https://resources.echocow.cn/prompt/logo-line.png"/>
      <div class="home-nav-action xs-hide q-mr-sm-xl lt-q-mr-none">
        <q-btn class="home-nav-button" style="margin-right: 24px" flat rounded color="white" label="注册"/>
        <q-btn class="home-nav-button" unelevated rounded color="primary" label="登陆"/>
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
    <div class="home-category column">
      <div class="home-category-row row no-wrap overflow-hidden">
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊阿斯顿发是打发是 克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋发的风格啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊克啊沙发上的割发代首复古风
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
      </div>
      <div class="home-category-row row no-wrap overflow-hidden">
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊克阿斯顿发是打发
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔的啊
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛格啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋啊大叔发上的割发代首复古风
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博常常发出
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
        <q-btn class="home-category-item">
          <div class="home-category-content">
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
            赛博朋叔的啊克
            <q-icon size="20px" class="home-category-item-icon" name="img:https://resources.echocow.cn/prompt/%23.svg" />
          </div>
        </q-btn>
      </div>
    </div>
    <div class="home-our row justify-between items-center">
      <q-img height="72px" width="384px" fit="contain" src="https://resources.echocow.cn/prompt/logo-line.png" />
      <div>
        二维码
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import HomeAi from 'pages/HomePage/HomeAi.vue'

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

  .home-category {
    background-color: #061938;
    display: flex;
    gap: 40px;
    padding: 80px 0 50px;
    align-items: center;

    .home-category-row {
      gap: 40px;
      width: 100%;
      justify-content: center;

      .home-category-item {
        background-color: #ffffff22;
        height: 88px;
        font-size: 28px;
        white-space: nowrap;
        border-radius: 45px;
        flex-wrap: nowrap;

        .home-category-content {
          margin: 0 20px;
        }
        .home-category-item-icon {
          margin: 0 5px;
        }

      }
    }
  }
  .home-our {
    margin: 40px 240px 80px;
  }
}
</style>
