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
              <h1 class="title text-center">공지사항</h1>
            </div>
          </div>
          <b-row class="mt-4 mb-2 text-center">
            <b-col></b-col>
            <b-col class="sm-3">
              <md-field>
                <md-icon>search</md-icon>
                <md-input
                  v-model="word"
                  placeholder="제목, 내용"
                  @keyup.enter="searchN"
                ></md-input>
              </md-field>
            </b-col>
            <b-col class="sm-3">
              <md-button
                style="float: left; font-family: 'IBMPlexSansKR-Regular'"
                class="md-info"
                @click="searchN"
                >검색</md-button
              >
              &nbsp; &nbsp; &nbsp; &nbsp;
              <md-button
                style="
                  float: left;
                  font-family: 'IBMPlexSansKR-Regular';
                  margin-left: 10px;
                "
                class="md-draw"
                @click="regist"
                v-if="userInfo != null && userInfo.id == 'admin'"
                >등록</md-button
              >
            </b-col>
          </b-row>
          <br />
          <br />
          <div>
            <b-nav tabs align="center">
              <b-nav-item @click="load(0)"
                >전체({{ this.list0.length }})</b-nav-item
              >
              <b-nav-item @click="load(1)"
                >공지({{ this.list1.length }})</b-nav-item
              >
              <b-nav-item @click="load(2)"
                >이벤트({{ this.list2.length }})
              </b-nav-item>
              <b-nav-item @click="load(3)"
                >기타({{ this.list3.length }})</b-nav-item
              >
            </b-nav>
          </div>

          <section>
            <b-table
              id="newstable"
              head-variant="light"
              :items="curList"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              responsive="sm"
              style="font-size: 12px; font-family: 'IBMPlexSansKR-Regular'"
            >
              <colgroup>
                <col style="width: 15%" />
                <col style="width: 70%" />
                <col style="width: 15%" />
              </colgroup>
              <template #cell(번호)="data">
                {{ curList.length - perPage * (currentPage - 1) - data.index }}
              </template>
              <template #cell(제목)="data">
                <router-link
                  :to="{ name: 'NoticeDetail', params: { no: data.item.no } }"
                  class="link"
                  >[{{ data.item.type }}] {{ data.item.title }}</router-link
                >
              </template>
              <template #cell(등록일)="data">
                {{ data.item.regtime | dateFormat }}
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="NoticeList.length"
              :per-page="perPage"
              aria-controls="newstable"
            ></b-pagination>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { mapState, mapActions } from "vuex";
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
      curList: [], //현재 선택한 종류에 맞는 공지사항 목록,
      list0: [], //전체
      list1: [], //정보
      list2: [], //사이트
      list3: [], //기타
      fields: ["번호", "제목", "등록일"],
      currentPage: 1,
      perPage: 10,
      word: "",
    };
  },
  created() {
    this.getAllNotice();
    this.curList = this.NoticeList;
    this.length_Notice = this.NoticeList.length;
  },
  computed: {
    ...mapState(noticeStore, ["NoticeList"]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  watch: {
    NoticeList: function () {
      this.curList = this.NoticeList;
      this.list0 = [];
      this.list1 = [];
      this.list2 = [];
      this.list3 = [];
      this.curList.forEach((notice) => {
        this.list0.push(notice);
        if (notice.type == "공지") {
          this.list1.push(notice);
        } else if (notice.type == "이벤트") {
          this.list2.push(notice);
        } else if (notice.type == "기타") {
          this.list3.push(notice);
        }
      });
    },
  },
  methods: {
    ...mapActions(noticeStore, ["getAllNotice"]),
    searchN() {
      if (this.word == "") this.getAllNotice();
      else this.$store.dispatch("noticeStore/searchNotice", this.word);
    },
    regist() {
      this.$router.push({
        name: "NoticeCreate",
      });
    },
    load(val) {
      console.log("load 결과 ");
      console.log(val);
      if (val == 0) {
        this.curList = this.list0;
      } else if (val == 1) {
        this.curList = this.list1;
      } else if (val == 2) {
        this.curList = this.list2;
      } else {
        this.curList = this.list3;
      }
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
table {
  table-layout: fixed !important;
}
.md-theme-default a:not(.md-button) {
  color: #166dc7;
}

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
