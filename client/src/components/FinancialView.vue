<template>
  <v-container class="grey lighten-5">
    <v-flex>
      <h2
        class="font-weight-black text-uppercase display-3 purple accent-3 text-center"
      >ข้อมูลด้านการเงิน</h2>
    </v-flex>
    <br />

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

    <div class="text-center">
      <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="financial">Back</v-btn>
    </div>
  </v-container>
</template>

<script>
import http from "../http-common"
export default {
  name: "FinancialView",
  data() {
    return {
      headers: [
        { text: "Financial ID", value: "finanId" },
        { text: "ชื่อนักศึกษา", value: "studentprofile.namethai" },
        { text: "ปีการศึกษา", value: "academicy" },
        { text: "รับงินจาก", value: "moneyformparent.parent" },
        { text: "รายได้ต่อครัวเรือน", value: "houseincome" },
        { text: "การใช้เงินนักศึกษา", value: "spendmoney.usemoney" },
        { text: "ระดับการใช้เงิน", value: "levelofuse.used" },
        { text: "กรณีใช้เงินไม่พอ", value: "notenough" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    financial() {
        this.$router.push("/financial")
    },
    getFinancial() {
      http.get("/financialinfo").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getFinancial();
  }
}
</script>