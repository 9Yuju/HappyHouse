<template>
  <div>
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
      style="height: 300px"
    >
      <div class="container">
        <div class="md-layout"></div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div
              class="
                md-layout-item md-size-66 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <h1 class="title text-center">공지사항 상세보기</h1>
            </div>
          </div>

          <div
            class="container"
            style="
              margin-top: 50px;
              font-size: 12px;
              font-family: 'IBMPlexSansKR-Regular';
            "
          >
            <md-field>
              <label>제목</label>
              <md-input
                v-model="notice.title"
                v-bind:disabled="isDisabled"
              ></md-input>
            </md-field>

            <md-field>
              <label for="type">종류</label>
              <md-select
                v-model="notice.type"
                name="type"
                id="type"
                v-bind:disabled="isDisabled"
              >
                <md-option value="공지">공지</md-option>
                <md-option value="이벤트">이벤트</md-option>
                <md-option value="기타">기타</md-option>
              </md-select>
            </md-field>

            <md-field>
              <label>내용</label>
              <md-textarea
                style="height: 500px"
                v-model="notice.content"
                v-bind:disabled="isDisabled"
              ></md-textarea>
            </md-field>
          </div>

          <div class="btnWrap">
            <span class="addContainer" @click="modifyNotice">
              <md-button
                class="md-default"
                v-if="userInfo != null && userInfo.id == 'admin'"
                >수정</md-button
              > </span
            >&nbsp;&nbsp;
            <span class="addContainer" @click="deleteNotice">
              <md-button
                class="md-default"
                v-if="userInfo != null && userInfo.id == 'admin'"
                >삭제</md-button
              > </span
            >&nbsp;&nbsp;
            <span class="addContainer" @click="back">
              <md-button class="md-default">뒤로</md-button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";
const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  props: {
    header: {
      type: String,
      default: require("@/assets/images/navhouse2.jpg"),
    },
  },
  data() {
    return {
      notice: {
        title: "",
        content: "",
        regtime: "",
        type: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
    isDisabled() {
      if (this.userInfo != null && this.userInfo.id == "admin") return false;
      else return true;
    },
  },
  methods: {
    ...mapActions(noticeStore, ["createNotice", "getNotice"]),
    modifyNotice() {
      if (this.notice.title != "" && this.notice.content != "") {
        http
          .put(`/notice/${this.$route.params.no}`, null, {
            params: this.notice,
          })
          .then(({ data }) => {
            this.notice = data;
            console.log(this.notice);
          });
        alert("수정되었습니다.");
        this.read();
      } else {
        alert("빈 칸 없이 입력해주세요.");
      }
    },
    deleteNotice() {
      if (confirm("삭제하시겠습니까?")) {
        http.delete(`/notice/${this.$route.params.no}`).then(({ data }) => {
          this.notice = data;
          console.log(this.notice);
          this.$router.go(-1);
        });
      }
    },
    read() {
      http.get(`/notice/${this.$route.params.no}`).then(({ data }) => {
        this.notice = data;
        console.log(this.notice);
      });
    },

    back() {
      this.$router.go(-1);
    },
  },
  created() {
    this.read();
  },
};
</script>

<style></style>
