<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <nav>
        <ul>
          <li>
            <router-link :to="{ name: 'Main' }" class="link hh">
              HappyHouse
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'NoticeList' }" class="link">
              공지사항
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'NewsList' }" class="link">
              뉴스
            </router-link>
          </li>
          <li>
            <router-link :to="{ name: 'QnaList' }" class="link">
              1:1문의
            </router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright" style="font-size: 12px">
        &copy; {{ year }}, made by Yuju Park, Juyeon Park
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String,
  },
  data() {
    return {
      year: "2022",
    };
  },
};
</script>
<style scoped>
.hh {
  font-weight: bold;
  font-size: 18px;
  font-family: "Lucida Console", "Courier New", monospace;
  margin: 0px 20px;
}
.link:hover,
li:hover,
router-link:hover {
  color: rgb(20, 168, 255) !important;
}
</style>
