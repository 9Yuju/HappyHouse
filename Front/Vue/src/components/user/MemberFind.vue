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
              <h5 slot="title" class="mb-0 mt-0">&nbsp;</h5>
              <h1 slot="title" class="card-title">비밀번호 찾기</h1>
              <p slot="description" class="description">
                Welcome to HappyHouse
              </p>

              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>ID</label>
                <md-input v-model="finduser.id"></md-input>
              </md-field>
              <md-field
                class="md-form-group"
                slot="inputs"
                style="text-align: center; padding-top: 20px"
              >
                <md-icon><b-icon icon="blank"></b-icon></md-icon>
                <p style="padding-left: 10px; font-size: 13px">
                  <b-icon icon="star-fill" font-scale="1"></b-icon>
                  &nbsp; 회원가입시 등록한 질문과 답변을 입력하세요.
                </p>
              </md-field>

              <md-field class="md-form-group" slot="inputs">
                <md-icon
                  ><b-icon
                    icon="question-circle"
                    font-scale="1"
                    variant="dark"
                  ></b-icon
                ></md-icon>
                <label for="que">Question</label>
                <md-select
                  v-model="finduser.que"
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
                <label>Anwser</label>
                <md-input v-model="finduser.ans"></md-input>
              </md-field>
              <md-button
                slot="footer"
                style="color: #ffd300 !important"
                class="md-simple md-success md-lg mb-0 p-0"
                @click="confirm"
              >
                제출
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { LoginCard } from "@/components";

const memberStore = "memberStore";

export default {
  name: "MemberJoin",
  components: {
    LoginCard,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/login.jpg"),
    },
  },
  computed: {
    ...mapState(memberStore, ["isExist", "Password"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  data() {
    return {
      tabs: 0,
      items: ["아이디", "비밀번호"],
      finduser: {
        id: null,
        que: null,
        ans: null,
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["EXIST"]),

    async confirm() {
      console.log(this.finduser.que);
      //
      if (this.finduser.id == null) alert("아이디를 입력해주세요.");
      else if (this.finduser.que == null) alert("질문을 선택해 주세요");
      else if (this.finduser.ans == null) alert("답변을 입력해 주세요");
      else {
        await this.EXIST(this.finduser);

        if (!this.isExist) {
          alert("입력한 정보를 확인해 주세요.");
        } else {
          alert("비밀번호 : " + this.Password);
          this.movePage();
        }
      }
    },
    movePage() {
      this.$router.push({ name: "SignIn" });
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
