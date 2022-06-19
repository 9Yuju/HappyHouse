<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container mt-4">
        <div class="md-layout mt-4">
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
              <h5 slot="title" class="mb-0 mt-0">&nbsp;</h5>
              <h1 slot="title" class="card-title">마이페이지</h1>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>ID</label>
                <md-input v-model="userInfo.id" readonly="readonly"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password</label>
                <md-input v-model="user.password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>person</md-icon>
                <label>Name</label>
                <md-input
                  v-model="user.name"
                  :placeholder="userInfo.name"
                ></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>e-mail</label>
                <md-input
                  v-model="user.email"
                  :placeholder="userInfo.email"
                ></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>phone</md-icon>
                <label>Phone</label>
                <md-input
                  v-model="user.tel"
                  :placeholder="userInfo.tel"
                ></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon
                  ><b-icon
                    icon="question-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <label> {{ userInfo.que }}</label>
                <md-select
                  v-model="user.que"
                  name="que"
                  id="que"
                  style="padding-left: 10px"
                >
                  <md-option value="자신의 반려동물 이름은?"
                    >자신의 반려동물 이름은?</md-option
                  >
                  <md-option value="가장 기억에 남는 선생님 성함은?"
                    >가장 기억에 남는 선생님 성함은?</md-option
                  >
                  <md-option value="인상 깊게 읽은 책 이름은?"
                    >인상 깊게 읽은 책 이름은?</md-option
                  >
                  <md-option value="자신이 가장 살고 싶은 주거구역은?"
                    >자신이 가장 살고 싶은 주거구역은?</md-option
                  >
                </md-select>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon
                  ><b-icon
                    icon="check2-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <label>답변</label>
                <md-input
                  v-model="user.ans"
                  :placeholder="userInfo.ans"
                ></md-input>
              </md-field>

              <md-button
                slot="footer"
                class="md-simple md-success md-lg mb-0 p-0"
                style="color: #ffd300 !important"
                @click="update"
              >
                정보수정
              </md-button>
              <md-button
                v-if="userInfo.id != 'admin'"
                slot="footer"
                class="md-simple md-success md-lg mb-0 p-0"
                style="color: #ffd300 !important"
                @click="deleteone"
              >
                회원탈퇴
              </md-button>
              <md-button
                v-if="userInfo.id == 'admin'"
                slot="footer"
                class="md-simple md-success md-lg mb-0 p-0"
                style="color: #ffd300 !important"
                @click="moveSearchAll"
              >
                회원목록보기
              </md-button>
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
  name: "MemberMyPage",
  components: { LoginCard },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/login.jpg"),
    },
    img: {
      type: String,
      default: require("@/assets/images/avatar.jpg"),
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        password: "",
        address: "",
        email: "",
        tel: "",
        que: "",
        ans: "",
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["DELETEONE"]),
    ...mapActions(memberStore, ["UPDATE"]),
    ...mapActions(memberStore, ["SEARCHALL"]),
    async update() {
      this.user.id = this.userInfo.id;
      if (this.user.name == "") this.user.name = this.userInfo.name;
      if (this.user.password == "") this.user.password = this.userInfo.password;
      if (this.user.email == "") this.user.email = this.userInfo.email;
      if (this.user.tel == "") this.user.tel = this.userInfo.tel;
      if (this.user.que == "") this.user.que = this.userInfo.que;
      if (this.user.ans == "") this.user.ans = this.userInfo.ans;
      await this.UPDATE(this.user);
      alert("수정이 완료되었습니다.");
      this.$router.go(-1);
    },
    async deleteone() {
      if (confirm("탈퇴하시겠습니까?")) {
        await this.DELETEONE(this.userInfo.id);
        alert("탈퇴가 되었습니다.");
        this.$router.go(-1);
      }
    },
    movePage() {
      this.$router.go(this.$router.currentRoute);
    },
    moveSearchAll() {
      console.log("이동");
      this.$router.push({ name: "All" });
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
