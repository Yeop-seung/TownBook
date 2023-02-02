/*!

=========================================================
* Black Dashboard React v1.2.1
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Book from "views/Book.js";
import Icons from "views/Icons.js";
import Map from "views/Map.js";
import Notice from "views/Notice.js";
// import Rtl from "views/Rtl.js";
import TableList from "views/TableList.js";
// import Typography from "views/Typography.js";
import UserProfile from "views/UserProfile.js";
// import SignUp from "views/SignUp";
import SignUpForm from "views/SignUpForm.js";
import Login from "views/Login.js";
import NoticeWrite from "views/NoticeWrite.js";
import IdFind from "views/IdFind.js";
// import First from "views/First.js";


var routes = [
  {
    path: "/dashboard",
    name: "메인페이지",
    rtlName: "لوحة القيادة",
    icon: "tim-icons icon-chart-pie-36",
    component: Book,
    layout: "/"
  },
  {
    path: "/icons",
    name: "커뮤니티",
    rtlName: "الرموز",
    icon: "tim-icons icon-atom",
    component: Icons,
    layout: "/"
  },
  {
    path: "/map",
    name: "도서검색",
    rtlName: "خرائط",
    icon: "tim-icons icon-pin",
    component: Map,
    layout: "/"
  },
  {
    path: "/notice",
    name: "공지사항",
    rtlName: "إخطارات",
    icon: "tim-icons icon-bell-55",
    component: Notice,
    layout: "/"
  },
  {
    path: "/user-profile",
    name: "마이페이지",
    rtlName: "ملف تعريفي للمستخدم",
    icon: "tim-icons icon-single-02",
    component: UserProfile,
    layout: "/"
  },
  {
    path: "/tables",
    name: "랭킹",
    rtlName: "قائمة الجدول",
    icon: "tim-icons icon-puzzle-10",
    component: TableList,
    layout: "/"
  },
  // {
  //   path: "/typography",
  //   name: "Typography",
  //   rtlName: "طباعة",
  //   icon: "tim-icons icon-align-center",
  //   component: Typography,
  //   layout: "/admin"
  // },
  {
    path: "/sign-up",
    name: "회원가입",
    // rtlName: "طباعة",
    icon: "tim-icons icon-align-center",
    component: SignUpForm,
    layout: "/"
  },
  {
    path: "/login",
    name: "로그인",
    // rtlName: "طباعة",
    icon: "tim-icons icon-align-center",
    component: Login,
    layout: "/"
  },
  {
    path: "/noticewrite",
    name: "공지작성",
    // rtlName: "طباعة",
    icon: "tim-icons icon-align-center",
    component: NoticeWrite,
    layout: "/"
  },
  {
    path: "/idfind",
    name: "아이디/비밀번호 찾기",
    // rtlName: "طباعة",
    icon: "tim-icons icon-align-center",
    component: IdFind,
    layout: "/"
  },
  // {
  //   path: "/first",
  //   name: "퍼스트",
  //   // rtlName: "طباعة",
  //   icon: "tim-icons icon-align-center",
  //   component: First,
  //   layout: "/kiosk"
  // },
];
export default routes;
