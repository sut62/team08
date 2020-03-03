<template>
  <v-app>
    <v-app-bar app dark >
      <v-toolbar dense style="z-index: 99">
      <v-toolbar-title>SYSTEM: SCHOLARSHIP</v-toolbar-title>
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
          <h1 class="display-2 font-weight-bold mb-3">Scholarship Register</h1>
        </v-flex>
      </v-layout>
    </div>



    <div>
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
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="6">


                <v-select
                  label="กรุณาเลือกนักศึกษา"
                  outlined
                  v-model="scholarship.studentproId"
                  :items="studentprofiles"
                  item-text="namethai"
                  item-value="studentproId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                <v-select
                  label="กรุณาเลือกประเภททุนการศึกษา"
                  outlined
                  v-model="scholarship.scholarshipTypeId"
                  :items="scholarshiptypes"
                  item-text="goodEducation"
                  item-value="scholarshipTypeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                <v-text-field
                  label="กรุณาป้อนจำนวนเงินทุน"
                  outlined
                  type="text"
                  v-model="scholarship.money"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <v-select
                  label="กรุณาเลือกเจ้าหน้าที่ งานทุน"
                  outlined
                  v-model="scholarship.officerId"
                  :items="scholarshipOfficers"
                  item-text="officername"
                  item-value="officerid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>               
                <v-menu v-model="menu1" :close-on-content-click="false" full-width max-width="290">
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
                  <v-date-picker locale="th" v-model="scholarship.paydate" @change="menu1 = false"></v-date-picker>
                </v-menu>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-btn @click="savescholarship" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
        <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
        <v-btn style="margin-left: 15px;" color="indigo" dark v-on:click="scholarshipview">View Scholarship</v-btn>
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
      return this.scholarship.paydate
        ? moment(this.scholarship.paydate).format("dddd Do, MMMM YYYY")
        : ""
    }
  },
  name: "scholarship",
  data() {
    return {
      scholarship: {
        scholarshipTypeId: "",
        studentproId: "",
        officerId: "",
        money: "",
        paydate: "",
      },
      studentprofiles: [],
      scholarshipOfficers: [],
      scholarshiptypes: [],
      menu1: false,
      saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },    
  methods: {
    scholarshipview() {
        this.$router.push("/scholarshipview")
    },
    clear() {
      this.scholarship.scholarshipTypeId = ""
      this.scholarship.studentproId = ""
      this.scholarship.officerId = ""
      this.scholarship.name = ""
      this.scholarship.paydate = ""
      this.scholarship.scholar = ""
    },
    //   @PostMapping("/scholarship/{scholarshipTypeid}/{stuid}/{officerid}/{name}/{paydate}/{scholar}")
    savescholarship() {
      http
        .post(
          "/scholarship/" +
            this.scholarship.scholarshipTypeId +
            "/" +
            this.scholarship.studentproId +
            "/" +
            this.scholarship.officerId +
            "/" +
            this.scholarship.money +
            "/" +
            this.scholarship.paydate,
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
    getScholarshipOfficers() {
      http
        .get("/scholarshipOfficers")
        .then(response => {
          this.scholarshipOfficers = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getScholarshipTypes() {
      http
        .get("/scholarshiptypes")
        .then(response => {
          this.scholarshiptypes = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    refreshList() {
      this.getStudentProfile()
      this.getScholarshipOfficers()
      this.getScholarshipTypes()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getStudentProfile()
    this.getScholarshipOfficers()
    this.getScholarshipTypes()
  }
}
</script>