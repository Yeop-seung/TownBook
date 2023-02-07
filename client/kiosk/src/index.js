import React from 'react';
import ReactDOM from 'react-dom/client';
import Main from './kiosk/Main'
import Finish from "./kiosk/Finish";
import DonateUse from "./kiosk/Donate/DonateUse";
import MemberSelect from "./kiosk/MemberSelect";
import DonateConfirm from "./kiosk/Donate/DonateConfirm";
import DonateComplete from "./kiosk/Donate/DonateComplete";
import DonateThanks from "./kiosk/Member/DonateThanks";
import BarcodeRead from "./kiosk/Donate/BarcodeRead"
import BarcodeReadError from "./kiosk/Donate/BarcodeReadError"
import ReceiptConfirm from "./kiosk/Receipt/ReceiptConfirm";
import ReceiptComplete from "./kiosk/Receipt/ReceiptComplete";
import DonateReceipt from "./kiosk/Receipt/DonateReceipt";
import ReceiptThanks from "./kiosk/Member/ReceiptThanks";
// import DonateThanksNon from "./kiosk/Non-Member/DonateThankNon";
import { BrowserRouter, Route, Routes } from "react-router-dom";
// import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='/kiosk' element={<Main />}/>
      <Route path='/kiosk/Finish' element={<Finish />}/>
      <Route path='/kiosk/MemberSelect' element={<MemberSelect />}/>
      <Route path='/kiosk/DonateUse' element={<DonateUse />}/>
      <Route path='/kiosk/DonateConfirm' element={<DonateConfirm />}/>
      <Route path='/kiosk/DonateComplete' element={<DonateComplete />}/>
      <Route path='/kiosk/DonateThanks' element={<DonateThanks />}/>
      <Route path='/kiosk/BarcodeRead' element={<BarcodeRead />}/>
      <Route path='/kiosk/BarcodeReadError' element={<BarcodeReadError />}/>
      <Route path='/kiosk/ReceiptConfirm' element={<ReceiptConfirm />}/>
      <Route path='/kiosk/ReceiptComplete' element={<ReceiptComplete />}/>
      <Route path='/kiosk/DonateReceipt' element={<DonateReceipt />}/>
      <Route path='/kiosk/ReceiptThanks' element={<ReceiptThanks />}/>
      {/* <Route path='/DonateThanksNon' element={<DonateThanksNon />}/> */}
      
    </Routes>
  </BrowserRouter>
);

reportWebVitals();
