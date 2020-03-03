<template>
  <div class="main">
    <div class="img">
      <v-row align="center" justify="center">
        <v-img max-width="190" max-height="190" :src="images.login"></v-img>
      </v-row>
    </div>

    <div class="body">
      <v-form v-model="valid">
        <v-container>
          <v-row>
            <v-col cols="12" md="4">
              <v-text-field
                label="Enter username"
                type="text"
                v-model="member.user"
                required
                outlined
              ></v-text-field>
              <br />

              <v-text-field
                v-model="member.pass"
                label="Enter password"
                :append-icon="value ? 'visibility' : 'visibility_off'"
                @click:append="() => (value = !value)"
                :type="value ? 'password' : 'text'"
                required
                outlined
              ></v-text-field>
              <br />
              <div class="my-2">
                <v-btn @click="logg" primary large block color="warning" dark>LOGIN</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </div>
  </div>
</template>

<script>
import http from "../http-common"

export default {
  name: "Login",
  data() {
    return {
      images: {
        login: require("../assets/users.png")
      },
      member: {
        user: "",
        pass: ""
      },
      value: true
    }
  },

  methods: {
    logg() {
      if (this.member.user === "") {
        alert("กรุณาป้อนชื่อผู้ใช้")
        return
      }
      if (this.member.pass === "") {
        alert("กรุณาป้อนรหัสผ่าน")
        return
      }
      http.get("/user/" + this.member.user + "/" + this.member.pass).then(res => {
        if (res.data) {
          alert("เข้าสู่ระบบสำเร็จ")
          switch (res.data.userType.userType) {
            case "นักศึกษา":
              this.$router.push("/student")
              break;
            case "เจ้าหน้างานทุน":
              this.$router.push("/scholarship")
              break;
            case "เจ้าหน้าที่งานวินัย":
              this.$router.push("/discipline")
              break;
            case "เจ้าหน้าที่กิจการนักศึกษา":
              this.$router.push("/activities")
              break;
          }
        } else {
          alert("ไม่สามารถเข้าสู่ระบบ")
        }
      })
    }
  }
}
</script>
<style type="text/css">
.img {
  margin-top: 6%;
  margin-left: 45%;
}
.body {
  margin-left: 41%;
}
</style>




