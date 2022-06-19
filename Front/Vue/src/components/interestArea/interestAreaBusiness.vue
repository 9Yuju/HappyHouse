<template>
  <div>
    <b-row>
      <b-container>
        <b-row class="my-1">
          <b-col class="sm-4">
            <label for="radius" style="font-size: 14px"
              >반경(km) : <b>{{ radius }} km</b></label
            >
            <b-form-input
              id="radius"
              v-model="radius"
              type="range"
              min="0.5"
              max="2"
              step="0.5"
            ></b-form-input>
          </b-col>
          <b-col class="sm-8">
            <b-form-group
              label="분류:"
              v-slot="{ ariaDescribedby }"
              style="font-size: 14px"
            >
              <b-form-checkbox-group
                id="checkbox"
                v-model="selected"
                :options="options"
                :aria-describedby="ariaDescribedby"
                name="types"
              ></b-form-checkbox-group>
            </b-form-group>
          </b-col>
        </b-row>
      </b-container>
    </b-row>
    <div class="overflow-auto" v-if="infos.length != 0">
      <b-table
        hover
        id="infotable"
        head-variant="light"
        :items="infos"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        @row-clicked="link"
        responsive="sm"
        style="font-size: 12px; font-family: 'IBMPlexSansKR-Regular'"
      >
        <template #cell(점포명)="data">
          {{ data.item.bizesNm }}
        </template>
        <template #cell(분류)="data">
          {{ data.item.indsLclsNm }}
        </template>
        <template #cell(분류상세)="data">
          {{ data.item.indsMclsNm }}
        </template>
        <template #cell(주소)="data">
          {{ data.item.rdnmAdr }}
        </template>
      </b-table>
      <b-pagination
        v-model="currentPage"
        :total-rows="infos.length"
        :per-page="perPage"
        aria-controls="infotable"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import store from "@/store/index.js";

const interestAreaStore = "interestAreaStore";

export default {
  name: "interestAreaBusiness",
  data() {
    return {
      url: "",
      fields: [
        {
          key: "점포명",
        },
        {
          key: "분류",
        },
        {
          key: "분류상세",
          label: "상세 분류",
        },
        {
          key: "주소",
          label: "도로명 주소",
        },
      ],
      currentPage: 1,
      perPage: 10,
      keyWord: "",
      radius: 0.5,
      selected: [], // 체크 박스 저장 배열
      options: [
        { text: "교육", value: "교육" },
        { text: "음식", value: "음식" },
        { text: "생활", value: "생활" },
        { text: "여가", value: "여가" },
        { text: "스포츠", value: "스포츠" },
      ],
    };
  },
  created() {
    store.commit("interestAreaStore/CLEAR_INFOS");
  },
  computed: {
    ...mapState(interestAreaStore, ["latlng", "infos"]),
  },
  watch: {
    latlng: function () {
      this.getInfos();
    },
    selected: function () {
      this.getInfos();
    },
    radius: function () {
      this.getInfos();
    },
  },
  methods: {
    link(item) {
      console.log("link 진입");
      const googleURL = "https://map.kakao.com/link/";
      let place = `${item.rdnmAdr} ${item.bizesNm}`;
      const url = googleURL + "search/" + place;

      window.open(url);
    },
    getInfos() {
      console.log("interestAreaBusiness - getInfos");
      console.log(this.latlng.cx, this.selected.length);
      if (this.latlng.cx != null && this.selected.length != 0) {
        console.log("interestAreaBusiness - getInfos");
        //얻은 값으로 상권 데이터 조회 후 목록 얻어오기
        store.dispatch("interestAreaStore/getInfos", {
          latlng: this.latlng,
          radius: this.radius,
          selected: this.selected,
        });
        console.log(this.infos);
      }
    },
  },
};
</script>

<style></style>
