import Main from "Kiosk/Main.js";
import Finish from "Kiosk/Finish.js";

const routes = [
  {
    path: "/kiosk",
    name: "Kiosk",
    component: Main,
    layout: "/KioskAdminLayout"
  },
  {
    path: "/black-dashboard-react/finish",
    name: "Finish",
    component: Finish,
    layout: "/KioskAdminLayout"
  },
];
export default routes;
