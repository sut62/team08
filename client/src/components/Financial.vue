<template>
  <v-app>
    <v-app-bar app dark class="indigo">
      <v-toolbar-title class="headline text-uppercase">
        <span>System :</span>
        <span class="font-weight-light">Financial Information</span>
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
          <h1 class="display-2 font-weight-bold mb-3">Financial Information</h1>
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
                  label="กรุณาเลือกนักศึกษา"
                  outlined
                  v-model="financialinfo.studentproId"
                  :items="studentprofiles"
                  item-text="namethai"
                  item-value="studentproId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="กรุณาป้อนปีการศึกษา"
                  outlined
                  type="text"
                  v-model="financialinfo.academicy"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกรับงินจาก..."
                  outlined
                  v-model="financialinfo.moneyparentId"
                  :items="moneyformparents"
                  item-text="parent"
                  item-value="moneyparentId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-text-field
                  label="กรุณาป้อนรายได้ต่อครัวเรือน"
                  outlined
                  type="text"
                  v-model="financialinfo.houseincome"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>

                <v-select
                  label="กรุณาเลือกการใช้เงินนักศึกษา"
                  outlined
                  v-model="financialinfo.spendId"
                  :items="spendmoneys"
                  item-text="usemoney"
                  item-value="spendId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                <v-select
                  label="กรุณาเลือกระดับการใช้เงิน"
                  outlined
                  v-model="financialinfo.levelId"
                  :items="levelofuses"
                  item-text="used"
                  item-value="levelId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>

                  <v-text-field
                    label="กรุณาป้อนกรณีใช้เงินไม่พอ"
                    outlined
                    type="text"
                    v-model="financialinfo.notenough"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                ></v-text-field>

              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>


      <v-row justify="center">
        <v-btn @click="saveFinancialInfo" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
        <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
        <v-btn style="margin-left: 15px;" @click="viewdata">viewdata</v-btn>
      </v-row>
      <br />
    </div>
  </v-app>
</template>

<script>
import http from "../http-common"

export default {
  name: "financialinfo",
  data() {
    return {
      financialinfo: {
        studentproId: "",
        academicy: "",
        moneyparentId: "",
        houseincome: "",
        spendId: "",
        levelId: "",
        notenough: ""
      },
      students: [],
      levelofuses: [],
      spendmoneys: [],
      moneyformparents: [],
      saveStatus: {
        isSuccess: false,
        isFail: false,
        message: ""
      }
    }
  },
  methods: {
    viewdata() {
        this.$router.push("/financialview")
    },
    clear() {
      (this.financialinfo.studentproId = ""),
        (this.financialinfo.academicy = ""),
        (this.financialinfo.moneyparentId = ""),
        (this.financialinfo.houseincome = ""),
        (this.financialinfo.spendId = "")
      this.financialinfo.levelId = ""
      this.financialinfo.notenough = ""
    },
    saveFinancialInfo() {
      if (this.financialinfo.notenough === "") {
        this.financialinfo.notenough = "Enough"
      }
      http
        .post(
          "/financialinfo/" +
            this.financialinfo.studentproId +
            "/" +
            this.financialinfo.academicy +
            "/" +
            this.financialinfo.moneyparentId +
            "/" +
            this.financialinfo.houseincome +
            "/" +
            this.financialinfo.spendId +
            "/" +
            this.financialinfo.levelId +
            "/" +
            this.financialinfo.notenough
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

    getMoneyFormParent() {
      http
        .get("/moneyformparents")
        .then(response => {
          this.moneyformparents = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    getSpendMoney() {
      http
        .get("/spendmoneys")
        .then(response => {
          this.spendmoneys = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    getLevelofUse() {
      http
        .get("/levelofuses")
        .then(response => {
          this.levelofuses = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },

    refreshList() {
      this.getStudentProfile()
      this.getMoneyFormParent()
      this.getSpendMoney()
      this.getLevelofUse()
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getStudentProfile()
    this.getMoneyFormParent()
    this.getSpendMoney()
    this.getLevelofUse()
  }
}
</script>