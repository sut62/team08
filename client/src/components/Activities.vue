  <template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">Activities</span>
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
          <h1 class="display-2 font-weight-bold mb-3">Activities</h1>
        </v-flex>
      </v-layout>
    </div>

    <div>
      <v-row justify="center">
        <v-col cols="7">
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="6">
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
      menu1: false
    }
  },
  methods: {
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
          console.log(response)
          alert("บันทึกข้อมมูลสำเร็จ")
        })
        .catch(e => {
          console.log(e)
        })
      this.submitted = true
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
