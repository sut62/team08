<template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">StudentProfile</span>
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

    <div v-if="!submitted">
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">StudentProfile</h1>
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
                <v-text-field
                  label="กรุณาป้อนรหัสนักศึกษา"
                  outlined
                  type="String"
                  v-model="Studentprofile.idcard"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกสาขาวิชา"
                  outlined
                  v-model="Studentprofile.majorId"
                  :items="major"
                  item-text="major"
                  item-value="majorId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  id="v-select-major"
                ></v-select>

                <v-text-field
                  label="กรุณาป้อนชื่อ-นามสกุล(ไทย)"
                  outlined
                  type
                  v-model="Studentprofile.namethai"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-text-field
                  label="กรุณาป้อนชื่อ-นามสกุล(อังกฤษ)"
                  outlined
                  type
                  v-model="Studentprofile.nameeng"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-text-field
                  label="กรุณาป้อนเลขบัตรประชาชน"
                  outlined
                  type
                  v-model="Studentprofile.idnumber"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

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
                          label="วัน/เดือน/ปี เกิด"
                          readonly
                          prepend-icon=" "
                          v-on="on"
                          locale="th"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        locale="th"
                        v-model="Studentprofile.birthday"
                        @change="menu1 = false"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>
                </v-row>

                <v-text-field
                  label="กรุณาป้อนอายุ"
                  outlined
                  type
                  v-model="Studentprofile.age"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกเพศ"
                  outlined
                  v-model="Studentprofile.genderId"
                  :items="gender"
                  item-text="gender"
                  item-value="genderId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="กรุณาใส่กรุ๊ปเลือด"
                  outlined
                  type
                  v-model="Studentprofile.blood"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกสถานภาพ"
                  outlined
                  v-model="Studentprofile.statusId"
                  :items="status"
                  item-text="status"
                  item-value="statusId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="กรุณาป้อนเลขโทรศัพท์"
                  outlined
                  type
                  v-model="Studentprofile.tel"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-text-field
                  label="กรุณาป้อนที่อยู่"
                  outlined
                  type
                  v-model="Studentprofile.address"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-btn @click="saveStudentprofile" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
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
  ////////////////////////////////นี่ด้วย//////////////////////
  computed: {
    computedDateFormattedMomentjs() {
      return this.Studentprofile.birthday
        ? moment(this.Studentprofile.birthday).format("dddd Do, MMMM YYYY")
        : ""
    }
  },
  ////////////////////////////////////////////ถึงนี่////////////////////////////////////////////////
  name: "Studentprofile",
  data() {
    return {
      Studentprofile: {
        idnumber: "",
        namethai: "",
        nameeng: "",
        idcard: "",
        birthday: "",
        age: "",
        blood: "",
        tel: "",
        address: "",
        majorId: "",
        genderId: "",
        statusId: ""
      },
      submitted: false,
      valid: false,
      major: [],
      status: [],
      gender: [],
      /////////////////////////// // ส่วนของ DatePicker///////////////////////////////////
      menu1: false,
      saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
      //////////////////////////////////////////////////////////////////////////////////////////////////
    }
  },
  methods: {
    saveStudentprofile() {
      http
        .post(
          "/studentprofile/" +
            this.Studentprofile.majorId +
            "/" +
            this.Studentprofile.birthday +
            "/" +
            this.Studentprofile.genderId +
            "/" +
            this.Studentprofile.statusId +
            "/" +
            this.Studentprofile.idnumber +
            "/" +
            this.Studentprofile.namethai +
            "/" +
            this.Studentprofile.nameeng +
            "/" +
            this.Studentprofile.idcard +
            "/" +
            this.Studentprofile.age +
            "/" +
            this.Studentprofile.blood +
            "/" +
            this.Studentprofile.tel +
            "/" +
            this.Studentprofile.address,
          this.Studentprofile
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
      this.Studentprofile = {
        idnumber: "",
        namethai: "",
        nameeng: "",
        idcard: "",
        birthday: "",
        age: "",
        blood: "",
        tel: "",
        address: "",
        majorId: "",
        genderId: "",
        statusId: ""
      }
      this.refreshList()
    },
    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data
        })
        .catch(err => {
          console.log("Can not get Gender ", err)
        })
    },
    getStatus() {
      http
        .get("/status")
        .then(response => {
          this.status = response.data
        })
        .catch(err => {
          console.log("Can not get Status ", err)
        })
    },
    getMajor() {
      http
        .get("/major")
        .then(response => {
          this.major = response.data
        })
        .catch(err => {
          console.log("Can not get Major", err)
        })
    },

    refreshList() {
      this.getGender()
      this.getStatus()
      this.getMajor()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getGender()
    this.getStatus()
    this.getMajor()
  }
}
</script>