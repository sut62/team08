<template>
  <v-app>
    <v-app-bar app dark >
      <v-toolbar dense style="z-index: 99">
      <v-toolbar-title>SYSTEM: ACTIVITIES</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon :to="{name: 'login'}">
        <v-icon>mdi-account-arrow-left</v-icon>
      </v-btn>
    </v-toolbar>
    </v-app-bar>
 
    <br />
    <br />
    <br />
    <br />

    <div>
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">Activities</h1>
        </v-flex>
      </v-layout>
    </div>

    <div>
      <v-row justify="center">
        <v-col cols="7">
          <v-row justify="center">
            <v-col cols="5">
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
              <v-col cols="5">


                <v-select
                  label="กรุณาเลือกชื่อนักศึกษา"
                  outlined
                  v-model="activities.studentid"
                  :items="students"
                  item-text="namethai"
                  item-value="studentproId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-select
                  label="กรุณาเลือกปีการศึกษา"
                  outlined
                  v-model="activities.yearid"
                  :items="schoolyears"
                  item-text="scyear"
                  item-value="yearid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-row>
                  <v-col>
                    <v-menu
                      v-model="menu1"
                      :close-on-content-click="false"
                      full-width
                      max-width="290"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          :value="computedDateFormattedMomentjs"
                          clearable
                          label="วัน/เดือน/ปี"
                          readonly
                          prepend-icon=" "
                          v-on="on"
                          locale="th"
                        ></v-text-field>
                      </template>
                      <v-date-picker locale="th" v-model="activities.date" @change="menu1 = false"></v-date-picker>
                    </v-menu>
                  </v-col>
                </v-row>

                <v-text-field
                  label="กรุณาป้อนชื่อกิจกรรม/โครงการ"
                  outlined
                  type="text"
                  v-model="activities.actname"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกหน่วยงานที่จัด"
                  outlined
                  v-model="activities.insid"
                  :items="institutions"
                  item-text="insname"
                  item-value="insid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                
                <v-text-field
                  label="กรุณาป้อนเบอร์โทรศัพท์มือถือ"
                  outlined
                  type="text"
                  v-model="activities.tel"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-btn @click="saveActivities" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
        <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
        <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="activitiesview">View Activities</v-btn>
      </v-row>
      <br />
    </div>
  </v-app>
</template>

<script>
import http from "../http-common"
import moment from "moment" ////////////////ใส่นี่อีกวันที่/////////////////////////////////

export default {
  computed: {
    computedDateFormattedMomentjs() {
      return this.activities.date
        ? moment(this.activities.date).format("dddd Do, MMMM YYYY")
        : ""
    }
  },
  name: "activities",
  data() {
    return {
      activities: {
        insid: "",
        yearid: "",
        studentid: "",
        actname: "",
        date: ""
      },
      schoolyears: [],
      institutions: [],
      students: [],
      submitted: false,
      valid: false,
      menu1: false,
      saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  methods: {
    activitiesview() {
        this.$router.push("/activitiesview")
    },
    getInstitutions() {
      http
        .get("/institution")
        .then(response => {
          this.institutions = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getSchoolYears() {
      http
        .get("/schoolyear")
        .then(response => {
          this.schoolyears = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getStudents() {
      http
        .get("/student")
        .then(response => {
          this.students = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    saveActivities() {
      http
        .post(
          "/activities/" +
            this.activities.studentid +
            "/" +
            this.activities.yearid +
            "/" +
            this.activities.date +
            "/" +
            this.activities.actname +
            "/" +
            this.activities.insid +
            "/" +
            this.activities.tel,
          this.activities
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
      this.getInstitutions()
      this.getSchoolYears()
      this.getStudents()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getInstitutions()
    this.getSchoolYears()
    this.getStudents()
  }
}
</script>