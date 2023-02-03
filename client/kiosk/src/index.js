import React from 'react';
import ReactDOM from 'react-dom/client';
import Main from './Kiosk/Main'
import Finish from "./Kiosk/Finish";
import DonateUse from "./Kiosk/Donate/DonateUse";
import MemberSelect from "./Kiosk/MemberSelect";
import DonateConfirm from "./Kiosk/Donate/DonateConfirm";
import DonateConfirmError from "./Kiosk/Donate/DonateConfirmError";
import DonateComplete from "./Kiosk/Donate/DonateComplete";
import DonateThanks from "./Kiosk/Member/DonateThanks";
// import DonateThanksNon from "Kiosk/Non-Member/DonateThankNon";
// import ReceiptThanks from "Kiosk/Member/ReceiptThanks";
// import DonateReceipt from "Kiosk/Donate/DonateReceipt";
// import ReceiptConfirm from "Kiosk/Receipt/ReceiptConfirm";
// import ReceiptComplete from "Kiosk/Receipt/ReceiptComplete";
import { BrowserRouter, Route, Routes } from "react-router-dom";
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
      <Route path='/DonateConfirmError' element={<DonateConfirmError />}/>
      <Route path='/DonateComplete' element={<DonateComplete />}/>
      <Route path='/DonateThanks' element={<DonateThanks />}/>
      {/* <Route path='/DonateThanksNon' element={<DonateThanksNon />}/>
      <Route path='/ReceiptThanks' element={<ReceiptThanks />}/>
      <Route path='/DonateReceipt' element={<DonateReceipt />}/>
      <Route path='/ReceiptConfirm' element={<ReceiptConfirm />}/>
      <Route path='/ReceiptComplete' element={<ReceiptComplete />}/> */}
    </Routes>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
