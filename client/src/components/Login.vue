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
      redirect: [
         {
          username: "aif",
          password: "1811",
          path: "/studentprofile"
        },
        {
          username: "nc",
          password: "1414",
          path: "/graduates"
        },
        {
          username: "may",
          password: "1234",
          path: "/studenthealthrecord"
        },
        {
          username: "boom",
          password: "1234",
          path: "/financial"
        },
        {
          username: "knpia",
          password: "1234",
          path: "/discipline"
        },
        {
          username: "june",
          password: "1234",
          path: "/activities"
        },
        {
          username: "fern",
          password: "1234",
          path: "/scholarship"
        }
      ],
      member: {
        user: "",
        pass: "",
        active: false
      },
      value: String,
      submitted: false,
      valid: false
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
      let isLogin = false
      this.redirect.forEach(direct => {
        if (
          direct.username === this.member.user &&
          direct.password === this.member.pass
        ) {
          isLogin = true
          this.$router.push(direct.path)
          alert("เข้าสู่ระบบสำเร็จ")
          return
        }
      })
      if (!isLogin) {
        alert("ไม่สามารถเข้าสู่ระบบ, กรุณาลองใหม่อีกครั้ง")
      }
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




