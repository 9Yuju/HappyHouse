<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="
              md-layout-item
              md-size-33
              md-small-size-66
              md-xsmall-size-100
              md-medium-size-40
              mx-auto
            "
          >
            <login-card header-color="green">
              <h1 slot="title" class="card-title">로그인</h1>
              <p slot="description" class="description mt-4 mb-4">
                Welcome to HappyHouse
              </p>

              <md-field class="md-form-group mt-4" slot="inputs">
                <md-icon class="test">face</md-icon>
                <label>ID</label>
                <md-input v-model="user.id"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password</label>
                <md-input
                  v-model="user.password"
                  type="password"
                  @keyup.enter="confirm"
                ></md-input>
              </md-field>
              <md-button
                slot="footer"
                style="color: #ffd300 !important"
                class="md-simple md-success md-lg m-0"
                @click="confirm"
                >로그인</md-button
              >
              <p
                slot="add"
                class="description"
                @click="movePage"
                style="cursor: pointer"
              >
                회원가입
              </p>
              <p slot="add" class="description">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </p>
              <p
                slot="add"
                class="description"
                @click="moveFind"
                style="cursor: pointer"
              >
                비밀번호 찾기
              </p>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { LoginCard } from "@/components";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  components: {
    LoginCard,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/login.jpg"),
    },
  },
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(memberStore, ["LOGIN"]),
    async confirm() {
      if (this.user.id == null) alert("아이디를 입력해주세요.");
      else if (this.user.password == null) alert("비밀번호를 입력해주세요.");
      else {
        await this.LOGIN(this.user);

        if (this.isLogin) {
          this.$router.push("/");
        } else alert("아이디 또는 비밀번호를 확인해주세요.");
      }
    },
    movePage() {
      this.$router.push({ name: "SignUp" });
    },
    removeNotify(e, notifyClass) {
      var target = e.target;
      while (target.className.indexOf(notifyClass) === -1) {
        target = target.parentNode;
      }
      return target.parentNode.removeChild(target);
    },
    moveFind() {
      this.$router.push({ name: "Find" });
    },
  },
};
</script>

<style scoped>
.md-field::after,
.md-field::before {
  background-color: rgb(65, 132, 229) !important;
}

.md-field.md-focused > .md-icon {
  color: rgb(65, 132, 229) !important;
}
</style>
