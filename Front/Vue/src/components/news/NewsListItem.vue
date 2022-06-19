<template>
  <b-tr>
    <b-td
      ><a :href="`${url}`" target="_blank" style="color: darkgreen">{{
        title
      }}</a></b-td
    >
    <b-td>{{ contents }}</b-td>
    <b-td>{{ time }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import http from "@/api/http";

export default {
  name: "QnaListItem",
  data() {},
  props: {
    title: String,
    contents: String,
    time: String,
    url: String,
  },
  created() {
    this.$store.dispatch("ALLNEWS");
    http.get(`/news`).then(({ data }) => {
      this.news = data;
      console.log(this.news);
    });
  },
};
</script>

<style></style>
