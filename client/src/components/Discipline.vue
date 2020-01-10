<template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">Discipline</span>
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
          <h1 class="display-2 font-weight-bold mb-3">Discipline</h1>
        </v-flex>
      </v-layout>
    </div>

    <div>
      <v-row justify="center">
        <v-col cols="7">
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="5">
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

                <v-text-field
                  label="เริ่มโทษ"
                  outlined
                  type="text"
                  v-model="discipline.since"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-text-field
                  label="สิ้นโทษ"
                  outlined
                  type="text"
                  v-model="discipline.until"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
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
export default {
  // computed: {
  //     computedDateFormattedMomentjs() {
  //         return this.date ? moment(this.date).format("dddd Do, MMMM YYYY") : "";
  //     }
  // },
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
      punishs: []
    }
  },
  methods: {
    clear() {
      this.discipline.studentproId = ""
      this.discipline.schoolyear = ""
      this.discipline.ruleId = ""
      this.discipline.point = ""
      this.discipline.punishId = ""
      this.discipline.since = ""
      this.discipline.until = ""
    },
    // @PostMapping("/discipline/{studentproId}/{schoolyear}/{ruleId}/{point}/{punishId}/{since}/{until}")
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
            this.discipline.until
        )
        .then(response => {
          console.log(response.data)
          if (response.data) {
            alert("บันทึกสำเร็จ")
            // this.$router.push("/view");
          } else {
            alert("บันทึกไม่สำเร็จ")
          }
        })
        .catch(e => {
          alert("บันทึกไม่สำเร็จ")
          console.log(e)
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