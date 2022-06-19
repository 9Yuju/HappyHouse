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
              <h1 class="title text-center">1:1문의</h1>
              <h3 class="description" style="font-family: Verdana">
                순차적으로 답변이 진행됩니다.
              </h3>
            </div>
          </div>

          <b-row class="mt-4 mb-4 text-center">
            <b-col></b-col>
            <b-col class="sm-3">
              <md-field>
                <md-icon>search</md-icon>
                <md-input
                  v-model="keyword"
                  placeholder="제목, 내용, 작성자"
                  @keyup.enter="search"
                ></md-input>
              </md-field>
            </b-col>
            <b-col class="sm-3">
              <md-button style="float: left" class="md-info" @click="search"
                >검색</md-button
              >
            </b-col>
          </b-row>
          <br />
          <br />

          <section>
            <b-table
              id="qnatable"
              head-variant="light"
              :items="articles"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              responsive="sm"
              style="font-size: 12px; font-family: 'IBMPlexSansKR-Regular'"
            >
              <template #cell(번호)="data">
                {{ articles.length - perPage * (currentPage - 1) - data.index }}
              </template>
              <template #cell(제목)="data" id="test">
                <router-link
                  :to="{
                    name: 'QnaDetail',
                    params: { articleno: data.item.articleno },
                  }"
                  style="color: rgb(65, 132, 229); font-weight: bold"
                  >{{ data.item.subject }}</router-link
                >
              </template>
              <template #cell(작성자)="data">
                {{ data.item.username }}
              </template>
              <template #cell(시간)="data">
                {{ data.item.regtime | dateFormat }}
              </template>
              <template #cell(답변)="data">
                {{ data.item.reply == null ? "답변대기" : "답변완료" }}
                <b-icon
                  v-if="data.item.reply != null"
                  icon="check"
                  font-scale="1"
                  variant="dark"
                ></b-icon>
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="articles.length"
              :per-page="perPage"
              aria-controls="newstable"
            ></b-pagination>
          </section>

          <div class="btnWrap">
            <span
              v-if="userInfo != null && userInfo.id != 'admin'"
              class="addContainer"
              @click="write"
              ><md-button class="md-default">작성</md-button></span
            >&nbsp;&nbsp;
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { mapState, mapActions } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "QnaList",
  components: {},
  props: {
    header: {
      type: String,
      default: require("@/assets/images/navhouse4.jpg"),
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD / HH:mm"); //22.05.25 / 12:55
    },
  },
  data() {
    return {
      word: "",
      fields: ["번호", "작성자", "제목", "시간", "답변"],
      currentPage: 1,
      perPage: 10,
      articles: [],
      reply: [],
    };
  },
  created() {
    http.get(`/qna/list`).then(({ data }) => {
      this.articles = data;
    });
  },
  computed: {
    ...mapState(["Qnas"]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
    ans(reply) {
      if (reply == null) {
        return "답변대기";
      } else return "답변완료";
    },
  },
  methods: {
    ...mapActions({}),
    search() {
      if (this.keyword == "") {
        http.get(`/qna/list`).then(({ data }) => {
          this.articles = data;
        });
      } else {
        http.get(`/qna/search/${this.keyword}`).then(({ data }) => {
          // console.log(data);
          this.articles = data;
        });
      }
    },
    write() {
      this.$router.push("/input");
    },
  },
};
</script>

<style scoped>
.del {
  text-decoration: line-through;
}
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}
li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
}
.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}
.removeBtn {
  margin-left: auto;
  color: #de4343;
}

.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>
