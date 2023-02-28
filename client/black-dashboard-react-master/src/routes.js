import Book from "views/Book.js";
import Icons from "views/Icons.js";
import Map from "views/Map.js";
import Notifications from "views/Notifications.js";
import TableList from "views/TableList.js";
import Typography from "views/Typography.js";
import UserProfile from "views/UserProfile.js";
import Main from "Kiosk/Main.js";
import Finish from "Kiosk/Finish.js";

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
    layout: "/"
  },
  {
    path: "/black-dashboard-react/finish",
    name: "Finish",
    component: Finish,
    layout: "/KioskAdminLayout"
  },
  
];
export default routes;
