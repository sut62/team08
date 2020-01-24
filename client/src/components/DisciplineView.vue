<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >Discipline View</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="discipline">Back</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "DisciplineView",
  data() {
    return {
      headers: [
        { text: "Discipline ID", value: "disciplineId" },
        { text: "ชื่อนักศึกษา", value: "studentprofile.namethai" },
        { text: "ปีการศึกษา", value: "schoolyear" },
        { text: "พฤติกรรมการกระทำผิด", value: "breakrule.ruleType" },
        { text: "คะแนนที่หัก", value: "point" },
        { text: "การลงโทษ", value: "punish.punishType" },
        { text: "เริ่มโทษ", value: "since" },
        { text: "สิ้นโทษ", value: "until" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    discipline() {
        this.$router.push("/discipline")
    },
    getDiscipline() {
      http.get("/discipline").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getDiscipline();
  }
}
</script>