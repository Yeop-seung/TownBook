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
import React from "react";
import ReactDOM from "react-dom/client";
// import Main from "Kiosk/Main";
// import DonateUse from "Kiosk/Login";
// import DonateComplete from "Kiosk/DonateComplete";
// import MemberSelect from "Kiosk/MemberSelect";
// import Finish from "Kiosk/Finish";
import DonateThanksNon from "Kiosk/Non-Member/DonateThankNon";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
// import DonateThanks from "Kiosk/DonateThanks";
// import 'react-app-polyfill/ie11';
// import 'react-app-polyfill/stable';


// import AdminLayout from "layouts/Admin/Admin.js";


// import "assets/scss/black-dashboard-react.scss";
// import "assets/demo/demo.css";
// import "assets/css/nucleo-icons.css";
// import "@fortawesome/fontawesome-free/css/all.min.css";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
      <BrowserRouter>
            <DonateThanksNon />
      </BrowserRouter>
);