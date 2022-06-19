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
              <h1 class="title text-center">전체 회원정보</h1>
            </div>
          </div>
          <br />
          <br />

          <section>
            <b-table
              hover
              id="usertable"
              head-variant="light"
              :items="users"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              responsive="sm"
              style="
                font-size: 11px;
                margin-left: auto !important;
                margin-right: auto !important;
                text-align: center !important;
              "
            >
              <template #cell(아이디)="data">
                {{ data.item.id }}
              </template>
              <template #cell(비밀번호)="data">
                {{ data.item.password }}
              </template>
              <template #cell(이름)="data">
                {{ data.item.name }}
              </template>
              <template #cell(전화번호)="data">
                {{ data.item.tel }}
              </template>
              <template #cell(이메일)="data">
                {{ data.item.email }}
              </template>
              <template #cell(PW질문)="data">
                {{ data.item.que }}
              </template>
              <template #cell(PW답변)="data">
                {{ data.item.ans }}
              </template>
              <template #cell(삭제)="data">
                <md-button
                  class="md-delete"
                  @click="deleteone(data.item.id)"
                  v-if="data.item.id != 'admin'"
                  >삭제</md-button
                >
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              :total-rows="users.length"
              :per-page="perPage"
              aria-controls="usertable"
            ></b-pagination>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "UserList",
  props: {
    header: {
      type: String,
      default: require("@/assets/images/navhouse6.jpg"),
    },
  },
  data() {
    return {
      fields: [
        "아이디",
        "비밀번호",
        "이름",
        "전화번호",
        "이메일",
        "PW질문",
        "PW답변",
        "삭제",
      ],
      currentPage: 1,
      perPage: 10,
      users: [],
      keyword: "",
      deleteId: "",
    };
  },
  created() {
    http.get(`/user/list`).then(({ data }) => {
      this.users = data;
      console.log(this.users);
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"], ["users"], ["DELETEUSER"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions({}),
    async deleteone(id) {
      if (confirm(id + " 을(를) 삭제하시겠습니까?")) {
        http.delete(`/user/delete/${id}`).then(({ data }) => {});
        alert("삭제가 완료 되었습니다.");
        http.get(`/user/list`).then(({ data }) => {
          this.users = data;
          console.log(this.users);
        });
      }
    },
  },
};
</script>

<style scoped>
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
