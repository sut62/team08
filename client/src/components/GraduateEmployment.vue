<template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">Graduate Employment Registration</span>
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
          <h1 class="display-2 font-weight-bold mb-3">Graduate Employment</h1>
        </v-flex>
      </v-layout>
    </div>

    <v-row justify="center">
      <v-col cols="7">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="5">
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
    <router-view />
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
      valid: false
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
          console.log(response.data)
          if (response.data) {
            alert("บันทึกสำเร็จ")
          } else {
            alert("บันทึกไม่สำเร็จ")
          }
        })
        .catch(e => {
          alert("บันทึกไม่สำเร็จ")
          console.log(e)
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
               