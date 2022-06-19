<template>
  <div id="app">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <router-view />
    </div>
    <router-view name="footer" />
  </div>
</template>

<script></script>

<style>
@font-face {
  font-family: "IBMPlexSansKR-Regular";
  src: url("https://cvdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.pagination {
  justify-content: center;
}
</style>
