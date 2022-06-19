<template>
  <div>
    <div class="mt-4 mb-4 text-center">
      <b-row align-h="center">
        <interest-Area-Search-Bar />
      </b-row>
    </div>
    <b-row class="m-3">
      <b-col cols="4"><ka-kao-map2 ref="KaKaoMap2" class="mr-0" /></b-col>
      <b-col cols="2" class="pl-0 pr-0"><interest-Area-List /> </b-col>
      <b-col cols="6" class="pr-4"><interest-Area-Business /> </b-col>
    </b-row>
  </div>
</template>

<script>
import interestAreaSearchBar from "@/components/interestArea/interestAreaSearchBar";
import KaKaoMap2 from "@/views/main/KaKaoMap2.vue";
import interestAreaList from "@/components/interestArea/interestAreaList";
import interestAreaBusiness from "@/components/interestArea/interestAreaBusiness";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  components: {
    interestAreaSearchBar,
    interestAreaList,
    KaKaoMap2,
    interestAreaBusiness,
  },
  created() {
    if (this.userInfo == null) {
      alert("로그인 후 확인가능합니다.");
      this.$router.push("/user/signin");
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
#category {
  position: absolute;
  top: 10px;
  left: 20px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
</style>
