<template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">Student Health Record information</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn class="ma-2" text icon color="lighten-2" :to="{name: 'home'}">
        <span class="mr-2">
          <v-icon large>home</v-icon>
        </span>
      </v-btn>
    </v-app-bar>
    <br />
    <br />
    <br />
    <br />

    <div>
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">Student Health Recordregistration</h1>
        </v-flex>
      </v-layout>
    </div>

   <v-row justify="center">
        <v-col cols="5">
          <v-row justify="center">
            <v-col cols="3">
              <div
                v-if="saveStatus.isSuccess"
                style="border: 1px solid #79FFBA; border-radius: 5px; background-color: #B2FFD7; align-items: center"
              >
                <div style="padding: 15px; color: #029E4E">{{saveStatus.message}}</div>
              </div>
              <div
                v-if="saveStatus.isFail"
                style="border: 1px solid #FFA879; border-radius: 5px; background-color: #FFD6B2; align-items: center"
              >
                <div style="padding: 15px; color: #733600">{{saveStatus.message}}</div>
              </div>
            </v-col>
          </v-row>
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="6">

              <v-select
                label="กรุณาเลือกนักศึกษา"
                outlined
                v-model="studenthealthRecord.studentproId"
                :items="student"
                item-text="namethai"
                item-value="studentproId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>



              <v-select
                label="กรุณาเลือกประวัติการแพ้ยา"
                outlined
                v-model="studenthealthRecord.drugallergyhistorysId"
                :items="drugallergyhistory"
                item-text="drugallergyhistory"
                item-value="drugallergyhistoryid"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>

              <v-text-field
                outlined
                type="number"
                v-model="studenthealthRecord.weights"
                label="กรุณาป้อนน้ำหนัก"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

              <v-text-field
                outlined
                type="number"
                v-model="studenthealthRecord.heights"
                label="กรุณาป้อนส่วนสูง"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

              <v-select
                label="กรุณาเลือกโรคประจำตัว"
                outlined
                v-model="studenthealthRecord.congenitaldiseasesId"
                :items="congenitalDisease"
                item-text="congenitaldisease"
                item-value="congenitaldiseaseid"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
              
            </v-col>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-btn @click="saveStudentHealthRecord" :class="{ red: !valid, green: valid }">submit</v-btn>
      <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
    </v-row>
    <br />

    <router-view />
  </v-app>
</template>

       <script>
import http from "../http-common"

export default {
  name: "StudentHealthRecord",
  data() {
    return {
      studenthealthRecord: {
        studentproId: "",
        weights: "",
        heights: "",
        congenitaldiseasesId: "",
        drugallergyhistorysId: "",
        active: false
      },
      submitted: false,
      student: [],
      drugallergyhistory: [],
      congenitalDisease: [],
       valid: false,
       saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  methods: {
    getStudentIds() {
      http
        .get("/student")
        .then(response => {
          this.student = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getCongenitalDisease() {
      http
        .get("/congenitalDisease")
        .then(res => {
          this.congenitalDisease = res.data
          console.log(res.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getDrugallergyhistorys() {
      http
        .get("/drugallergyhistory")
        .then(response => {
          this.drugallergyhistory = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    saveStudentHealthRecord() {
      http
        .post(
          "/StudentHealthRecord/" +
            this.studenthealthRecord.studentproId +

            "/" +
            this.studenthealthRecord.weights +
            "/" +
            this.studenthealthRecord.heights +
            "/" +
            this.studenthealthRecord.congenitaldiseasesId +
            "/" +
            this.studenthealthRecord.drugallergyhistorysId,
            this.studenthealthRecord
        )
         .then(response => {
          if (response) {
            this.saveStatus.message = "บันทึกข้อมูลสำเร็จ"
            this.saveStatus.isSuccess = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isSuccess = false
            }, 3000)
          } else {
            this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
            this.saveStatus.isFail = true
            setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 3000)
          }
        })
        .catch(() => {
          this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
          this.saveStatus.isFail = true
           setTimeout(() => {
              this.saveStatus.message = ""
              this.saveStatus.isFail = false
            }, 3000)
        })
    },
    clear() {
      this.$refs.form.reset()
    },
    refreshList() {
      this.getStudentIds()
      this.getDrugallergyhistorys()
      this.getCongenitalDisease()
    }
  },
  mounted() {
    this.getStudentIds()
    this.getDrugallergyhistorys()
    this.getCongenitalDisease()
  }
}
</script>
               