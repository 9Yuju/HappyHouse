<template>
  <div v-if="!isNull">
    <span
      style="
        margin-left: 10%;
        float: left;
        font-size: 15px;
        font-weight: bold;
        color: steelblue;
      "
    >
      인기 >
    </span>

    <vue-swimlane
      :words="populars"
      :circular="false"
      :rows="1"
      :scale="1"
      :transitionDuration="750"
      :transitionDelay="1200"
      :pauseOnHover="true"
      @click="clickApt"
      style="
        width: 400px !important;
        margin-top: 15px !important;
        color: steelblue !important;
        font-family: 'IBMPlexSansKR-Regular' !important;
        font-size: 20px !important;
      "
    ></vue-swimlane>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";

const interestAreaStore = "interestAreaStore";

export default {
  name: "interestAreaPopular",
  created() {
    // console.log("interestAreapopular - created()");
    this.populars = [];
    this.getPopularAreas();
    this.popularAreas.forEach((el, index) => {
      this.populars.push(`${index + 1}. ${el.juso}`);
    });
    // console.log(this.populars.length);
    // console.log(this.populars.toString);
    // console.log(this.populars);
    // console.log("----");
    this.isNull = this.populars.toString().includes("null");
    // console.log(this.isNull);
  },
  computed: {
    ...mapState(interestAreaStore, ["area", "popularAreas"]),
  },
  data() {
    return {
      populars: [],
      fields: [
        {
          key: "area",
          label: "인기 지역",
        },
      ],
      isNull: false,
    };
  },
  methods: {
    ...mapActions(interestAreaStore, ["getPopularAreas"]),
    ...mapMutations(interestAreaStore, ["SET_AREA"]),
    clickApt(area) {
      console.log("clickApt 작동!!!");
    },
  },
};
</script>

<style>
.vue-swimlane ul li {
  font-size: 16px !important;
}
.vue-swimlane ul:first-child {
  margin-top: 0px;
  padding-top: 8px;
}
</style>
