<template>
  <div>
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="
              md-layout-item md-size-45 md-small-size-70 md-xsmall-size-100
            "
          ></div>
          <div
            class="
              md-layout-item md-size-45 md-small-size-70 md-xsmall-size-100
            "
          >
            <h1
              class="title"
              style="
                color: rgb(65 132 229);
                font-size: 80px;
                text-shadow: 3px 3px 3px #444;
                text-align: right;
                font-family: 'SBAggroB';
              "
            >
              HappyHouse
            </h1>
            <h3
              style="
                color: rgb(50 50 50);
                font-size: 25px;
                text-align: right;
                font-family: 'GimpoGothicBold00';
              "
            >
              - 부동산 거래 정보 사이트 -
            </h3>
          </div>
        </div>
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
              <h1 class="title text-center">아파트 실거래가 조회</h1>
            </div>
          </div>
          <div class="features text-center">
            <div id="searchbar"></div>
            <b-row class="mt-4 mb-4 text-center">
              <b-col class="sm-3">
                <!-- <word /> -->
              </b-col>
              <b-col class="sm-3">
                <md-field>
                  <b-form-select
                    v-model="sidoCode"
                    :options="sidos"
                    @change="gugunList"
                    style="
                      height: 35px;
                      font-size: 11px;
                      font-family: 'IBMPlexSansKR-Regular';
                    "
                  ></b-form-select>
                </md-field>
              </b-col>
              <b-col class="sm-3">
                <md-field>
                  <b-form-select
                    v-model="gugunCode"
                    :options="guguns"
                    @change="dongList"
                    style="
                      height: 35px;
                      font-size: 11px;
                      font-family: 'IBMPlexSansKR-Regular';
                    "
                  ></b-form-select>
                </md-field>
              </b-col>
              <b-col class="sm-3">
                <md-field>
                  <b-form-select
                    v-model="dongCode"
                    :options="dongs"
                    @change="searchDongCode"
                    style="
                      height: 35px;
                      font-size: 11px;
                      font-family: 'IBMPlexSansKR-Regular';
                    "
                  ></b-form-select>
                </md-field>
              </b-col>
              <b-col class="sm-3">
                <md-field>
                  <label style="font-family: 'IBMPlexSansKR-Regular'"
                    >이름으로 검색</label
                  >
                  <md-input
                    v-model="aptName"
                    type="text"
                    @keyup.enter="search"
                  ></md-input>
                </md-field>
              </b-col>
              <md-button
                class="md-info md-sm"
                @click="search"
                style="
                  height: 35px;
                  margin-top: 12px;
                  font-size: 12px;
                  font-family: 'IBMPlexSansKR-Regular';
                  background-color: rgb(65, 132, 229) !important;
                "
                >검색</md-button
              >
              <b-col class="sm-3"> </b-col>
            </b-row>

            <div class="row">
              <div class="col-xl-5" v-if="houses2.length == 0">
                <b-table
                  hover
                  id="housetable"
                  head-variant="light"
                  v-b-toggle.sidebar-1
                  :items="houses"
                  :fields="fields"
                  :per-page="perPage"
                  :current-page="currentPage"
                  @row-clicked="clickHouse"
                  style="font-size: 11px"
                >
                  <template #cell(index)="data">
                    {{ data.index + 1 + (currentPage - 1) * perPage }}
                  </template>
                </b-table>

                <b-pagination
                  v-model="currentPage"
                  :total-rows="houses.length"
                  :per-page="perPage"
                  aria-controls="housetable"
                ></b-pagination>
              </div>

              <div class="col-xl-7 mb-5 mb-xl-0">
                <div class="card bg-default">
                  <ka-kao-map ref="KaKaoMap" />
                </div>
              </div>
            </div>

            <b-sidebar
              id="sidebar-1"
              v-if="house != null && houses2.length == 0 && houses.length != 0"
              shadow
            >
              <div class="container" style="margin-top: 60px">
                <md-button
                  class="md-info"
                  style="
                    font-weight: bold;
                    font-family: 'IBMPlexSansKR-Regular';
                    font-size: 15px;
                  "
                  >{{ this.house.aptName }}</md-button
                >
                <b-img
                  :src="require('../../assets/Banpo.jpg')"
                  fluid
                  style="margin-top: 10px"
                ></b-img>
                <table
                  class="tbAdd"
                  style="
                    font-size: 15px;
                    margin-top: 20px;
                    font-family: 'IBMPlexSansKR-Regular';
                  "
                >
                  <colgroup>
                    <col width="25%" />
                    <col width="*" />
                  </colgroup>
                  <tr>
                    <th>거래금액</th>
                    <td>{{ this.house.recentPrice }}</td>
                  </tr>
                  <tr>
                    <th>건축년도</th>
                    <td>{{ this.house.buildYear }}</td>
                  </tr>
                  <tr>
                    <th>법정동</th>
                    <td>{{ this.house.dongName }}</td>
                  </tr>
                  <tr>
                    <th>시군구</th>
                    <td>
                      {{ this.house.sidoName }} {{ this.house.gugunName }}
                    </td>
                  </tr>
                  <tr>
                    <th>지번</th>
                    <td>{{ this.house.jibun }}</td>
                  </tr>
                </table>
              </div>
            </b-sidebar>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import KaKaoMap from "./KaKaoMap.vue";
import http from "@/api/http";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: { KaKaoMap },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/house3.jpg"),
    },
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null, //이거 @@동 이름임
      code: null, // 이게 진짜 법정동 코드
      aptName: "", // 아파트 이름으로 검색용
      currentPage: 1,
      perPage: 12,
      searchAll: -1,
      apts: [],
      fields: [
        {
          key: "index",
          label: "번호",
        },
        {
          key: "aptName",
          label: "아파트",
        },
        {
          key: "jibun",
          label: "지번",
        },
        {
          key: "buildYear",
          label: "건축년도",
        },
        {
          key: "recentPrice",
          label: "거래금액",
        },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      // "codes",
      "houses",
      "houses2",
      "house",
    ]),
    ...mapState(memberStore, ["userInfo"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_HOUSE2_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getHouseName",
      "searchAptName",
    ]),
    // ...mapActions(wordStore, ["getWords"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_CODE_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_HOUSE2_LIST",
      "SET_DONG_NAME",
      "SET_HOUSE",
    ]),

    clickHouse(data) {
      this.SET_HOUSE(data);
    },

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.CLEAR_CODE_LIST();
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_HOUSE2_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.CLEAR_CODE_LIST();
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_HOUSE2_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    async searchDongCode() {
      //gugunCode 와 동이름으로 dongCode 찾기
      console.log("searchDongCode 호출");
      await http
        .get(`/map/code/${this.gugunCode}/${this.dongCode}`)
        .then(({ data }) => {
          this.code = data;
        });
      this.searchApt();
    },
    searchApt() {
      if (this.dongCode) {
        this.currentPage = 1;
        this.CLEAR_HOUSE_LIST();
        this.CLEAR_HOUSE2_LIST();
        this.aptName = "";
        this.SET_DONG_NAME(this.dongCode);
        console.log("searchApt() - 호출");
        this.getHouseList(this.code);
      }
    },
    search() {
      if (this.aptName == "") alert("검색어를 입력해주세요.");
      else {
        this.currentPage = 1;
        this.CLEAR_HOUSE_LIST();
        this.CLEAR_HOUSE2_LIST();
        this.searchAptName(this.aptName);
      }
    },
    click() {
      this.searchAll *= -1;
      this.search();
    },
  },
};
</script>

<style></style>
