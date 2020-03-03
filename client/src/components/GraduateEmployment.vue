<template>
  <v-app>
    <v-app-bar app dark >
      <v-toolbar dense style="z-index: 99">
      <v-toolbar-title>SYSTEM: GRADUATE EMPLOYMENT</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon :to="{name: 'login'}">
        <v-icon>mdi-account-arrow-left</v-icon>
      </v-btn>
    </v-toolbar>
    </v-app-bar>
   
    <br />
    <br />
    <br />

    <div>
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">Graduate Employment Register</h1>
        </v-flex>
      </v-layout>
    </div>

    <div>
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
                v-model="graduateemployment.studentId"
                :items="students"
                item-text="namethai"
                item-value="studentproId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>

              <v-select
                label="กรุณาเลือกสำเร็จการศึกษาระดับ"
                outlined
                v-model="graduateemployment.educaId"
                :items="educationlevels"
                item-text="educalevel"
                item-value="educaId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>

              <v-select
                label="กรุณาเลือกสถานะการทำงาน"
                outlined
                v-model="graduateemployment.workstatusId"
                :items="workstatus"
                item-text="status"
                item-value="workstatusId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>

              <v-text-field
                label="กรุณาป้อนตำแหน่งงาน"
                outlined
                type="text"
                v-model="graduateemployment.workplace"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

              <v-text-field
                label="กรุณาป้อนสถานที่ทำงาน"
                outlined
                type="text"
                v-model="graduateemployment.jopposition"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>

              <v-text-field
                label="กรุณาป้อน Email"
                outlined
                type="text"
                v-model="graduateemployment.email"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-btn @click="saveGraduateEmployment" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
      <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
    </v-row>
      <br />
    </div>
  </v-app>
</template>

       <script>
import http from "../http-common"

export default {
  name: "GraduateEmployment",
  data() {
    return {
      graduateemployment: {
        studentId: "",
        workstatusId: "",
        educaId: "",
        workplace: "",
        jopposition: "",
        email: ""
      },
      students: [],
      educationlevels: [],
      workstatus: [],
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

    getEducationlevels() {
      http
        .get("/educationlevel")
        .then(response => {
          this.educationlevels = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getWorkStatus() {
      http
        .get("/workstatus")
        .then(response => {
          this.workstatus = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    // @PostMapping("/graduateemployment/{studentproId}/{workstatusId}/{educaId}/{workplace}/{jopposition}/{email}")
    saveGraduateEmployment() {
      http
        .post(
          "/graduateemployment/" +
            this.graduateemployment.studentId +
            "/" +
            this.graduateemployment.workstatusId +
            "/" +
            this.graduateemployment.educaId +
            "/" +
            this.graduateemployment.workplace +
            "/" +
            this.graduateemployment.jopposition +
            "/" +
            this.graduateemployment.email,
          this.graduateemployment
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
      this.getStudents()
      this.getEducationlevels()
      this.getWorkStatus()
    }
  },
  mounted() {
    this.getStudents()
    this.getEducationlevels()
    this.getWorkStatus()
  }
}
</script>
               