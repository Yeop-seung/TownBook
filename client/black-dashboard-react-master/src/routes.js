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
import Notifications from "views/Notifications.js";
import TableList from "views/TableList.js";
import Typography from "views/Typography.js";
import UserProfile from "views/UserProfile.js";
import Main from "Kiosk/Main.js";

var routes = [
  {
    path: "/dashboard",
    name: "메인페이지",
    icon: "tim-icons icon-chart-pie-36",
    component: Book,
    layout: "/admin"
  },
  {
    path: "/icons",
    name: "커뮤니티",
    icon: "tim-icons icon-atom",
    component: Icons,
    layout: "/admin"
  },
  {
    path: "/map",
    name: "도서검색",
    icon: "tim-icons icon-pin",
    component: Map,
    layout: "/admin"
  },
  {
    path: "/notifications",
    name: "공지사항",
    icon: "tim-icons icon-bell-55",
    component: Notifications,
    layout: "/admin"
  },
  {
    path: "/user-profile",
    name: "마이페이지",
    icon: "tim-icons icon-single-02",
    component: UserProfile,
    layout: "/admin"
  },
  {
    path: "/tables",
    name: "랭킹",
    icon: "tim-icons icon-puzzle-10",
    component: TableList,
    layout: "/admin"
  },
  {
    path: "/typography",
    name: "Typography",
    icon: "tim-icons icon-align-center",
    component: Typography,
    layout: "/admin"
  },
  {
    path: "/kiosk",
    name: "kiosk",
    icon: "tim-icons icon-align-center",
    component: Main,
    layout: "/kiosk"
  },
  // {
  //   path: "/kiosk",
  //   name: "kiosk",
  //   icon: "tim-icons icon-align-center",
  //   component: Main,
  //   layout: "/kiosk"
  // },
  
];
export default routes;
