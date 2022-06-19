<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <router-link :to="{ name: 'Main' }" class="link">
          <h1 class="md-title fontB" style="font-weight: bold; font-size: 30px">
            HappyHouse
          </h1>
        </router-link>
      </div>
      <div class="md-toolbar-section-end">
        <div class="md-collapse">
          <div class="md-collapse-wrapper fontB">
            <md-list>
              <md-list-item v-if="this.isLogin && !this.isAdmin">
                <router-link
                  :to="{ name: 'InterestArea' }"
                  class="link"
                  style="font-size: 18px"
                >
                  관심지역</router-link
                >
              </md-list-item>

              <md-list-item>
                <router-link
                  :to="{ name: 'NoticeList' }"
                  class="link"
                  style="font-size: 18px"
                >
                  공지사항</router-link
                >
              </md-list-item>

              <md-list-item>
                <router-link
                  :to="{ name: 'NewsList' }"
                  class="link"
                  style="font-size: 18px"
                >
                  뉴스</router-link
                >
              </md-list-item>

              <md-list-item>
                <router-link
                  :to="{ name: 'QnaList' }"
                  class="link"
                  style="font-size: 18px"
                >
                  1:1문의</router-link
                >
              </md-list-item>

              <md-list-item> | </md-list-item>
              <md-list-item target="_blank" v-if="this.isLogin">
                <router-link
                  v-if="userInfo.id != 'admin'"
                  :to="{ name: 'MyPage' }"
                  style="
                    font-size: 18px;
                    color: rgb(65, 132, 229) !important;
                    font-weight: bold !important;
                  "
                >
                  {{ userInfo.name }}&nbsp;({{ userInfo.id }}) 님
                </router-link>
                <router-link
                  v-else
                  :to="{ name: 'All' }"
                  style="
                    font-size: 18px;
                    color: rgb(65, 132, 229) !important;
                    font-weight: bold !important;
                  "
                >
                  회원관리
                </router-link>
              </md-list-item>

              <md-list-item v-if="this.isLogin" @click.prevent="onClickLogout">
                <router-link to="/" class="link" style="font-size: 18px">
                  로그아웃
                </router-link>
              </md-list-item>

              <md-list-item v-if="!this.isLogin">
                <router-link
                  :to="{ name: 'SignIn' }"
                  class="link2 link"
                  style="font-size: 18px"
                >
                  로그인</router-link
                >
              </md-list-item>
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();
    }, 66);
  }
}

import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  components: {},
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info",
        ].includes(value);
      },
    },
    colorOnScroll: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isAdmin"]),
  },
  methods: {
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_USER_INFO",
      "SET_IS_ADMIN",
    ]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      this.SET_IS_ADMIN(false);
      this.$router.go(this.$router.currentRoute);
    },
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    },
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  },
};
</script>
<style scoped>
.md-toolbar.md-transparent .md-title,
.md-toolbar.md-transparent .md-list .md-list-item-content,
.md-toolbar.md-transparent .md-list-item-container .md-icon,
.md-toolbar.md-transparent,
.md-toolbar.md-transparent:hover,
.md-toolbar.md-transparent:focus {
  color: #000000c7 !important;
}
.link {
  color: #000000c7 !important;
}
.fontB {
  font-family: "GimpoGothicBold00" !important;
}
.fontR {
  font-family: "GimpoGothicRegular00";
}
.bar {
  color: black !important;
}
</style>
