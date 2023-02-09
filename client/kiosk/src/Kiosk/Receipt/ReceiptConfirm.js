import React, { useEffect, useMemo, useState } from "react";
import styles from "./ReceiptConfirm.module.css"
import { useLocation, useNavigate } from "react-router-dom";
import ItemList from "../../ui/Item";
// import { Route } from "react-router-dom";
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { BiHomeAlt } from 'react-icons/bi';
import axios from "axios";
import Table from "../../ui/Table";


function ReceiptConfirm(props) {
    const navigate = useNavigate()
    const location = useLocation()

    const isnavigate = location.state.isnavigate
    const Locker = location.state.Locker
    const lockerNo = location.state.Locker.lockerNo
    const User = location.state.User

    const [inputData, setInputData] = useState([{
        title: '',
        detailLocker: ''
    }])
    
    useEffect(async() => {
        try {
            const res = await axios.get(`http://i8b201.p.ssafy.io:8081/backend/book/locker/${lockerNo}`)
            console.log(res.data.data)
            let resData = await res.data.data.map((rowData) => ({
                title : resData.bookTitle,
                detailLocker: resData
            })
            )
        setInputData(inputData.concat(data))
        } catch(error) {
            console.log(error)
        }
        return {

        }
    })
    
    const columns = useMemo(
        () => [
            {
                accessor: "title",
                Header: "제목"
            },
            {
                accessor: "detailLocker",
                Header: "보관함 번호"
            },
            ],
            []
        );
    const data = useMemo(
        () => [{
            'title': '제목',
            'detailLocker': '1',
        }],[]);
    
    // async function check() {
    //     try {
    //     const response = await axios.get(`http://i8b201.p.ssafy.io:8081/backend/book/locker/${lockerNo}`)
    //     const data = response.data.data
    //     console.log('fffffff', data.length)
    //     for( let i = 1; i < data.length; i++){
    //         console.log('efefef', data[i])
    //         const title = data[i].bookTitle
    //         console.log('fffffff', title)
    //     }
    //     } catch (error) {
    //     console.error(error);
    //     }
    // }

    // check()
    
    const onClickHandlerHome = () => {
        navigate('/')
    }
    const goBack = () => {
        navigate(-1)
    }
    const onClickHandlerReceiptComplete = () => {
        navigate('/ReceiptComplete')
    }
    return (
        // 책 리스트 불러오기 
            <div>
                <div className={styles.myImg}>
                    <button className={styles.circle} onClick={goBack}>
                        <AiOutlineArrowLeft className={styles.iconStyle}/>
                    </button>
                    <div>
                        <div className={styles.buttonOne} >
                            <p className={styles.h3Text}>수령하실 책을 선택해주세요</p>
                        </div>
                        <div className={styles.table}>
                        <Table  columns={columns} data={data}/>
                        </div>
                    </div>
                    <button className={styles.homeCircle} onClick={onClickHandlerHome}>
                        <BiHomeAlt className={styles.iconStyle}/>
                    </button>
                </div>
            </div>
        )
}

export default ReceiptConfirm