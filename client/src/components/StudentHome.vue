<template>
 <v-app>
    <v-app-bar app dark >
      <v-toolbar dense style="z-index: 99">
      <v-toolbar-title>SYSTEM: FOR STUDENT</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon :to="{name: 'login'}">
        <v-icon>mdi-account-arrow-left</v-icon>
      </v-btn>
    </v-toolbar>
    </v-app-bar>
   
    <br />
    <br />
    <br />

    <div class="container-view">
      <v-navigation-drawer expand-on-hover height="100vh" class="side-nav">
        <v-list>
          <v-list-item class="px-2">
            <v-list-item-avatar>
              <v-img src="../assets/student-icon.png"></v-img>
            </v-list-item-avatar>
            <span style="padding: 10px"></span>
            <v-list-item-content>
              <v-list-item-title class="title">For Student</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list nav dense>
          <v-list-item link :to="{name: 'studentprofile'}">
            <v-icon>person</v-icon>
            <v-list-item-title>Student Profile</v-list-item-title>
          </v-list-item>
          <v-list-item link :to="{name: 'studenthealthrecord'}">
            <v-icon>mdi-hospital-box</v-icon>
            <v-list-item-title>Student Health Record</v-list-item-title>
          </v-list-item>
          <v-list-item link :to="{name: 'financial'}">
            <v-icon>mdi-cash-multiple</v-icon>
            <v-list-item-title>Financial</v-list-item-title>
          </v-list-item>
          <v-list-item link :to="{name: 'graduates'}">
            <v-icon>mdi-book</v-icon>
            <v-list-item-title>Graduates</v-list-item-title>
          </v-list-item>
          <v-list-item link :to="{name: 'graduateemployment'}">
            <v-icon>mdi-briefcase</v-icon>
            <v-list-item-title>Graduate Employment</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
      <div class="playground">
        <router-view></router-view>
      </div>
    </div>
</v-app>
</template>


<script>
export default {
  name: "StudentHome",
  data() {
    return {
      drawer: true,
      items: [
        { title: "Home", icon: "dashboard" },
        { title: "About", icon: "question_answer" }
      ]
    }
  },

  methods: {}
}
</script>
<style type="text/css">
.container-view {
  display: flex;
}
.side-nav {
  float: left;
  max-width: 256;
}
.playground {
  float: right;
  width: 100%;
}
</style>




