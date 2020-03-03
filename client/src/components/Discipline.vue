<template>
  <v-app>
    <v-app-bar app dark >
      <v-toolbar dense style="z-index: 99">
      <v-toolbar-title>SYSTEM: DISCIPLINE</v-toolbar-title>
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

    <div >
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">DISCIPLINE</h1>
        </v-flex>
      </v-layout>
    </div>

    <div>
      

          <v-row justify="center">
        <v-btn style="margin-left: 15px;" color="indigo"  v-on:click="disciplineview">View Discipline</v-btn>
      </v-row>

          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="6">
                <v-select
                  label="นักศึกษา"
                  outlined
                  v-model="discipline.studentproId"
                  :items="studentprofiles"
                  item-text="namethai"
                  item-value="studentproId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="ปีการศึกษา"
                  outlined
                  type="text"
                  v-model="discipline.schoolyear"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="พฤติกรรมการกระทำผิด"
                  outlined
                  v-model="discipline.ruleId"
                  :items="breakrules"
                  item-text="ruleType"
                  item-value="ruleId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="คะแนนที่ถูกตัด"
                  outlined
                  type="text"
                  v-model="discipline.point"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="การลงโทษ"
                  outlined
                  v-model="discipline.punishId"
                  :items="punishs"
                  item-text="punishType"
                  item-value="punishId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                  <h4>วันที่เริ่มโทษ:</h4>
                      <v-date-picker locale="th" v-model="discipline.since" @change="menu1 = false"></v-date-picker>
                <br  />
                <br  />    
                <br  />
                <h4>วันที่สิ้นโทษ:</h4>
                      <v-date-picker locale="th" v-model="discipline.until" @change="menu1 = false"></v-date-picker>
                    

              </v-col>
            </v-row>
          </v-form>
          <v-row justify="center">
        <v-col cols="7">
          <v-row justify="center">
            <v-col cols="6">
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
        </v-col>
      </v-row>
      
      <br />
      <v-row justify="center">
        <v-btn @click="saveDiscipline" :class="{ red: !valid, green: valid }">save</v-btn>
        <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
      </v-row>
      <br />
    </div>
  </v-app>
</template>

<script>
import http from "../http-common"
import moment from "moment" 

export default {
  computed: {
    computedDateFormattedMomentjs() {
      return this.discipline.since
        ? moment(this.discipline.since).format("dddd Do, MMMM YYYY")
        : ""
    }
  },
  computed: {
    computedDateFormattedMomentjs() {
      return this.discipline.until
        ? moment(this.discipline.until).format("dddd Do, MMMM YYYY")
        : ""
    }
  },
   name: "discipline",
  data() {
    return {
      discipline: {
        studentproId: "",
        schoolyear: "",
        ruleId: "",
        point: "",
        punishId: "",
        since: "",
        until: ""
      },
      studentprofiles: [],
      breakrules: [],
      punishs: [],
      menu: false,
      menu1: false,
      submitted: false,
      valid: false,
      saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  methods: {
     disciplineview() {
      this.$router.push("/disciplineview")
    },
    getStudentProfile() {
      http
        .get("/student")
        .then(response => {
          this.studentprofiles = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getBreakrule() {
      http
        .get("/breakrules")
        .then(response => {
          this.breakrules = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getPunish() {
      http
        .get("/punishs")
        .then(response => {
          this.punishs = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    saveDiscipline() {
      http
        .post(
          "/discipline/" +
            this.discipline.studentproId +
            "/" +
            this.discipline.schoolyear +
            "/" +
            this.discipline.ruleId +
            "/" +
            this.discipline.point +
            "/" +
            this.discipline.punishId +
            "/" +
            this.discipline.since +
            "/" +
            this.discipline.until,
          this.discipline
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
      this.getStudentProfile()
      this.getBreakrule()
      this.getPunish()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getStudentProfile()
    this.getBreakrule()
    this.getPunish()
  }
}
</script>