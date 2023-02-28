import React from "react";
import ReactDOM from "react-dom/client";
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

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
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