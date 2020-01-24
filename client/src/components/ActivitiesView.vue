<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h1
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >Activities View</h1>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="activities">Back</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "ActivitiesView",
  data() {
    return {
      headers: [
        { text: "Activities ID", value: "actid" },
        { text: "ชื่อนักศึกษา", value: "studentprofile.namethai" },
        { text: "ปีการศึกษา", value: "schoolyear.scyear" },
        { text: "วันที่จัดกิจกรรม", value: "date" },
        { text: "ชื่อกิจกรรม/โครงการ", value: "actname" },
        { text: "หน่วยงานที่จัด", value: "institution.insname" },
        { text: "เบอร์โทรศัพท์มือถือ", value: "tel" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    activities() {
        this.$router.push("/activities")
    },
    getActivities() {
      http.get("/activities").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getActivities();
  }
}
</script>