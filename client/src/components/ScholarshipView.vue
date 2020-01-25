<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >Scholarship View</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="scholarship">Back</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "ScholarshipView",
  data() {
    return {
      headers: [
        { text: "Scholarship ID", value: "scholarshipid" },
        { text: "ชื่อนักศึกษา", value: "studentProfile.namethai" },
        { text: "ประเภททุนการศึกษา", value: "scholarshipType.goodEducation" },
        { text: "จำนวนเงินทุน", value: "money" },
        { text: "เจ้าหน้าที่งานทุน", value: "scholarshipOfficer.officername" },
        { text: "วัน/เดือน/ปี", value: "paydate" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    scholarship() {
        this.$router.push("/scholarship")
    },
    getScholarship() {
      http.get("/scholarship").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getScholarship();
  }
}
</script>