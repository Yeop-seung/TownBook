import React from "react";
import "./Main.css"
import './Base.css'
// import { Route } from "react-router-dom";

// import MemberSelect from './MemberSelect'

const Main = (props) => {
    return (
            <div className="my-img">
                    <div className="button">
                        <div className="button-three">
                            <p className="text-align">도서 기부</p>
                        </div>
                    </div>
                <div className="button-one">
                    <div className="button-two">
                    <p className="text-align">도서 수령</p>
                    </div>
                </div>
            </div>
        )
}

export default Main