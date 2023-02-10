import axios from "axios";
import React, { useState, useEffect, useRef } from "react";


const ItemList = () => {
    let [data, setData] = useState([])
    const check = () => {
        
        axios.get(`http://i8b201.p.ssafy.io:8081/backend/book/locker/${lockerNo}`, {
        })
        .then((response) => {
            const responseData = response.data.data
            setData(responseData)
            console.log('gggggg',responseData)
        })
        .catch((error) => {
            console.log(error)
        })
    }

    return (
    <div>
        
    </div>
    );
    };

export default ItemList;