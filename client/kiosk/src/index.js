import React from 'react';
import ReactDOM from 'react-dom/client';
import Main from './Kiosk/Main'
import Finish from "./Kiosk/Finish";
import DonateUse from "./Kiosk/Donate/DonateUse";
import MemberSelect from "./Kiosk/MemberSelect";
import DonateConfirm from "./Kiosk/Donate/DonateConfirm";
import DonateComplete from "./Kiosk/Donate/DonateComplete";
import DonateThanks from "./Kiosk/Member/DonateThanks";
import BarcodeRead from "./Kiosk/Donate/BarcodeRead"
import BarcodeReadError from "./Kiosk/Donate/BarcodeReadError"
import ReceiptConfirm from "./Kiosk/Receipt/ReceiptConfirm";
import ReceiptComplete from "./Kiosk/Receipt/ReceiptComplete";
import DonateReceipt from "./Kiosk/Receipt/DonateReceipt";
import ReceiptThanks from "./Kiosk/Member/ReceiptThanks";
// import DonateThanksNon from "./Kiosk/Non-Member/DonateThankNon";
import { BrowserRouter, Route, Routes } from "react-router-dom";
// import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='/Kiosk' element={<Main />}/>
      <Route path='/Kiosk/Finish' element={<Finish />}/>
      <Route path='/Kiosk/MemberSelect' element={<MemberSelect />}/>
      <Route path='/Kiosk/DonateUse' element={<DonateUse />}/>
      <Route path='/Kiosk/DonateConfirm' element={<DonateConfirm />}/>
      <Route path='/Kiosk/DonateComplete' element={<DonateComplete />}/>
      <Route path='/Kiosk/DonateThanks' element={<DonateThanks />}/>
      <Route path='/Kiosk/BarcodeRead' element={<BarcodeRead />}/>
      <Route path='/Kiosk/BarcodeReadError' element={<BarcodeReadError />}/>
      <Route path='/Kiosk/ReceiptConfirm' element={<ReceiptConfirm />}/>
      <Route path='/Kiosk/ReceiptComplete' element={<ReceiptComplete />}/>
      <Route path='/Kiosk/DonateReceipt' element={<DonateReceipt />}/>
      <Route path='/Kiosk/ReceiptThanks' element={<ReceiptThanks />}/>
      {/* <Route path='/DonateThanksNon' element={<DonateThanksNon />}/> */}
      
    </Routes>
  </BrowserRouter>
);

reportWebVitals();
