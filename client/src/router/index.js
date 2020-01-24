import Vue from "vue";
import Router from "vue-router";
import Login from "../components/Login";
import Home from "../components/Home";
import StudentProfile from "../components/Studentprofile";
import Activities from "../components/Activities";
import Discipline from "../components/Discipline";
import Financial from "../components/Financial";
import StudentHealthRecord from "../components/StudentHealthRecord";
import Scholarship from "../components/Scholarship";
import DisciplineView from "../components/DisciplineView";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "login",
      component: Login
    },
    {
      path: "/home",
      name: "home",
      component: Home
    },
    {
      path: "/studentprofile",
      name: "studentprofile",
      component: StudentProfile
    },
    {
      path: "/activities",
      name: "activities",
      component: Activities
    },
    {
      path: "/discipline",
      name: "discipline",
      component: Discipline
    },
    {
      path: "/financial",
      name: "financial",
      component: Financial
    },
    {
      path: "/studenthealthrecord",
      name: "studenthealthrecord",
      component: StudentHealthRecord
    },

    {
      path: "/scholarship",
      name: "scholarship",
      component: Scholarship
    },
    {
      path: "/disciplineview",
      name: "disciplineview",
      component: DisciplineView
    }
  ]
});
