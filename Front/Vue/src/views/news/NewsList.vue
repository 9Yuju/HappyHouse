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
              <h1 class="title text-center">뉴스</h1>
            </div>
          </div>
          <b-row class="mt-4 mb-4 text-center">
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
            </b-col>
          </b-row>
          <br />
          <br />

          <section>
            <b-table
              id="newstable"
              head-variant="light"
              :items="news"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              responsive="sm"
              style="
                font-size: 14px;
                font-family: 'IBMPlexSansKR-Regular';
                width: 85%;
                margin: auto;
              "
              thead-class="d-none"
            >
              <template #cell(사진)="data">
                <a :href="`${data.item.url}`" target="_blank">
                  <img
                    :src="`${data.item.imgUrl}`"
                    alt="Image"
                    class="rounded img-fluid"
                    width="150"
                  />
                </a>
              </template>

              <template #cell(제목)="data">
                <a
                  :href="`${data.item.url}`"
                  target="_blank"
                  style="
                    color: #4184e5;
                    font-size: 18px;
                    line-height: 200%;
                    font-weight: bold;
                  "
                  >{{ data.item.title }}</a
                >
                <br />
                {{ data.item.contents }}
                <br />
                <div class="small" style="line-height: 300%">
                  {{ data.item.writing }} &nbsp;|&nbsp;
                  <div
                    v-if="
                      data.item.time.indexOf('분전') !== -1 ||
                      data.item.time.indexOf('1시') !== -1
                    "
                    class="recently"
                    style="line-height: 300%"
                  >
                    {{ data.item.time }}
                  </div>
                  <div v-else class="past" style="line-height: 300%">
                    {{ data.item.time }}
                  </div>
                </div>
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="news.length"
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
import http from "@/api/http";
const newsStore = "newsStore";
import NewsListItem from "@/components/news/NewsListItem.vue";

export default {
  components: {
    //  NewsListItem,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/navhouse3.jpg"),
    },
  },
  data() {
    return {
      fields: ["사진", "제목"],
      currentPage: 1,
      perPage: 10,
      word: "",
      news: [],
    };
  },
  created() {
    http.get(`/news`).then(({ data }) => {
      this.news = data;
      console.log(this.news);
    });
  },
  computed: {
    ...mapState(newsStore, ["NewsList"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(newsStore, ["ALLNEWS"]),
    searchN() {
      if (this.word == "") {
        http.get(`/news`).then(({ data }) => {
          this.news = data;
          console.log(this.news);
        });
      } else {
        http.get(`/news/search/${this.word}`).then(({ data }) => {
          this.news = data;
          console.log(this.news);
        });
      }
    },
    all() {
      console.log("NewsList", this.NewsList);
    },
  },
};
</script>

<style scoped>
body {
  width: 100%;
}
.div-size {
  width: 80%;
  margin-right: 0px;
}
.past {
  font-size: 12px;
  display: inline-block;
}
.recently {
  color: #354964;
  font-weight: bold;
  font-size: 12px;
  display: inline-block;
}
.small {
  font-size: 12px;
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
