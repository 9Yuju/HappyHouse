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
        <div class="container-fluid">
          <div class="md-layout">
            <div
              class="
                md-layout-item md-size-66 md-xsmall-size-100
                mx-auto
                text-center
              "
            >
              <h1 class="title text-center">관심지역 둘러보기</h1>
            </div>
          </div>
          <interest-area-main ref="interestAreaMain" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import interestAreaMain from "@/components/interestArea/interestAreaMain";

export default {
  name: "interestArea",
  components: {
    interestAreaMain,
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/images/navhouse5.jpg"),
    },
  },
  data() {},
  created() {},
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style></style>
