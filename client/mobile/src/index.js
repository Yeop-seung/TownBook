import React from "react";
import ReactDOM from "react-dom/client";
<<<<<<< HEAD:client/mobile/src/index.js
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import "react-app-polyfill/ie11";
import "react-app-polyfill/stable";
// import Admin from "layouts/Admin/Admin";

import AdminLayout from "layouts/Admin/Admin.js";
// import RTLLayout from "layouts/RTL/RTL.js";

import "assets/scss/black-dashboard-react.scss";
import "bootstrap/dist/css/bootstrap.css";
import "assets/demo/demo.css";
import "assets/css/nucleo-icons.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

import ThemeContextWrapper from "./components/ThemeWrapper/ThemeWrapper";
import BackgroundColorWrapper from "./components/BackgroundColorWrapper/BackgroundColorWrapper";
// import AdminNavbar from "components/Navbars/AdminNavbar";
=======
// import { QueryClient, QueryClientProvider } from "react-query";
// import {ReactQueryDevtools} from "react-query/devtools"
import Main from "Kiosk/Main";
import Finish from "Kiosk/Finish";
import DonateUse from "Kiosk/Donate/DonateUse";
import MemberSelect from "Kiosk/MemberSelect";
import DonateConfirm from "Kiosk/Donate/DonateConfirm";
import DonateConfirmError from "Kiosk/Donate/DonateConfirmError";
import DonateComplete from "Kiosk/Donate/DonateComplete";
import DonateThanks from "Kiosk/Member/DonateThanks";
// import DonateThanksNon from "Kiosk/Non-Member/DonateThankNon";
// import ReceiptThanks from "Kiosk/Member/ReceiptThanks";
// import DonateReceipt from "Kiosk/Donate/DonateReceipt";
// import ReceiptConfirm from "Kiosk/Receipt/ReceiptConfirm";
// import ReceiptComplete from "Kiosk/Receipt/ReceiptComplete";
import { BrowserRouter, Route, Routes } from "react-router-dom";
// import 'react-app-polyfill/ie11';
// import 'react-app-polyfill/stable';


// import KioskAdminLayout from "layouts/Admin/KioskAdmin.js";
>>>>>>> KI:client/black-dashboard-react-master/src/index.js

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
<<<<<<< HEAD:client/mobile/src/index.js
  <ThemeContextWrapper>
    <BackgroundColorWrapper>
      <BrowserRouter>
        <Switch>
          <Route path="/" render={(props) => <AdminLayout {...props} />} />
          <Redirect from="/" to="/dashboard" />
        </Switch>
      </BrowserRouter>
    </BackgroundColorWrapper>
  </ThemeContextWrapper>
);
=======
            <BrowserRouter>
                  <Routes>
                        <Route path="/" element={ <Main/>} />
                        <Route path="/finish" element={ <Finish/>} />
                        <Route path="/MemberSelect" element={ <MemberSelect/>} />
                        <Route path="/DonateUse" element={ <DonateUse/>} />
                        <Route path="/DonateConfirm" element={ <DonateConfirm/>} />
                        <Route path="/DonateConfirmError" element={ <DonateConfirmError/>} />
                        <Route path="/DonateComplete" element={ <DonateComplete/>} />
                        <Route path="/DonateThanks" element={ <DonateThanks/>} />
                        {/* <Route path="/DonateThanksNon" element={ <DonateThanksNon/>} /> */}
                        {/* <Route path="/ReceiptThanks" element={ <ReceiptThanks/>} /> */}
                        {/* <Route path="/DonateReceipt" element={ <DonateReceipt/>} /> */}
                        {/* <Route path="/ReceiptConfirm" element={ <ReceiptConfirm/>} /> */}
                        {/* <Route path="/ReceiptComplete" element={ <ReceiptComplete/>} /> */}
                        {/* <Route path="/finish">
                              <Finish />
                        </Route> */}
                  </Routes>
            </BrowserRouter>
            
      // <BrowserRouter>
      // <DonateReceipt/>
      /* <Finish /> */      
            
            // <DonateUse />
            // <MemberSelect />
            // <DonateComplete />
            // <DonateThanksNon />
            // <ReceiptThanks /> 
            // <DonateConfirm />
            // <DonateConfirmError />
            // <DonateThanks />
            // <ReceiptConfirm />
            // <ReceiptComplete />
            
      // </BrowserRouter>
);
>>>>>>> KI:client/black-dashboard-react-master/src/index.js
