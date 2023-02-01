import React from "react";
import "./DonateUse.css"
import "./Base.css"
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import {BsFillArrowRightCircleFill} from "react-icons/bs"

function DonateUse(props) {
    // function submitHandler(event) {
    //     event.preventDefault()
    // }
    return (
        // <div>
            <div>
                <div className="my-img">
                    <div className="circle">
                        <AiOutlineArrowLeft size={80} pull="right" color="#90C6FE"/>
                    </div>
                    <div className="button">
                        <div className="button-one">
                            <h3 className="h3-align">STEP1</h3>
                            <p className="text-align">마이페이지에서
                            <br />
                            QR코드를
                            <br />
                            발급해주세요</p>
                            <BsFillArrowRightCircleFill className="arrow-right" size={72}/>
                            <h3 className="h3-align-one">STEP2</h3>
                            <p className="text-align-two">바코드리더기에
                            <br />
                            QR코드를
                            <br />
                            찍어주세요</p>
                        </div>
                    </div>
                    <div className="home-circle">
                        <BiHomeAlt size={76} color="#90C6FE"/>
                    </div>
                </div>
            </div>
            /* <label htmlFor="title" onSubmit={}></label>
            <input type="text" required id="title" />
        </div> */
        )
}

export default DonateUse