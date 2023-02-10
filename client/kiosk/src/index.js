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
import DonateThanksNon from "./Kiosk/Non-Member/DonateThankNon";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import DoorClose from './Kiosk/DoorClose'; 
// import Table from './ui/Table';
// import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='/' element={<Main />}/>
      <Route path='/Finish' element={<Finish />}/>
      <Route path='/MemberSelect' element={<MemberSelect />}/>
      <Route path='/DonateUse' element={<DonateUse />}/>
      <Route path='/DonateConfirm' element={<DonateConfirm />}/>
      <Route path='/DonateComplete' element={<DonateComplete />}/>
      <Route path='/DonateThanks' element={<DonateThanks />}/>
      <Route path='/BarcodeRead' element={<BarcodeRead />}/>
      <Route path='/BarcodeReadError' element={<BarcodeReadError />}/>
      <Route path='/ReceiptConfirm' element={<ReceiptConfirm />}/>
      <Route path='/ReceiptComplete' element={<ReceiptComplete />}/>
      <Route path='/DonateReceipt' element={<DonateReceipt />}/>
      <Route path='/ReceiptThanks' element={<ReceiptThanks />}/>
      <Route path='/DonateThanksNon' element={<DonateThanksNon />}/>
      <Route path='/DoorClose' element={<DoorClose />}/>
    </Routes>
  </BrowserRouter>
);

reportWebVitals();
