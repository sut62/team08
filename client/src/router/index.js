import Vue from "vue";
import Router from "vue-router";
import Login from "../components/Login";
import StudentProfile from "../components/Studentprofile";
import Activities from "../components/Activities";
import Discipline from "../components/Discipline";
import Financial from "../components/Financial";
import StudentHealthRecord from "../components/StudentHealthRecord";
import Scholarship from "../components/Scholarship";
import DisciplineView from "../components/DisciplineView";
import FinancialView from "../components/FinancialView";
import Graduates from "../components/Graduates";
import ActivitiesView from "../components/ActivitiesView";
import GraduateEmployment from "../components/GraduateEmployment";
import ScholarshipView from "../components/ScholarshipView";
import StudentHome from "../components/StudentHome";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/student",
      name: "studenthome",
      component: StudentHome,
      children: [
        {
          path: "/studentprofile",
          name: "studentprofile",
          component: StudentProfile
        },
        {
          path: "/studenthealthrecord",
          name: "studenthealthrecord",
          component: StudentHealthRecord
        },
        {
          path: "/financial",
          name: "financial",
          component: Financial
        },
        {
          path: "/graduates",
          name: "graduates",
          component: Graduates
        },
        {
          path: "/graduateemployment",
          name: "graduateemployment",
          component: GraduateEmployment
        }
      ]
    },
    {
      path: "/",
      name: "login",
      component: Login
    },
    {
      path: "/login",
      name: "login",
      component: Login
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
      path: "/scholarship",
      name: "scholarship",
      component: Scholarship
    },
    {
      path: "/disciplineview",
      name: "disciplineview",
      component: DisciplineView
    },
    {
      path: "/financialview",
      name: "financialview",
      component: FinancialView
    },

    {
      path: "/activitiesview",
      name: "activitiesview",
      component: ActivitiesView
    },

    {
      path: "/scholarshipview",
      name: "scholarshipview",
      component: ScholarshipView
    }
  ]
});
