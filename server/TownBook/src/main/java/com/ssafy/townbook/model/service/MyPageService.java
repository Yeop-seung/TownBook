package com.ssafy.townbook.model.service;

import com.google.zxing.WriterException;
import com.ssafy.townbook.model.entity.BookLog;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MyPageService {

    Object getQrCode(String accountEmail) throws WriterException, IOException;

    int getPoint(Long accountNo) throws Exception;

    Optional<JSONArray> getAllLog(Long accountNo) throws Exception;
//
//    List<BookLog> getDonateLog(Long accountNo) throws Exception;
//
//    List<BookLog> getReceiveLog(Long accountNo) throws Exception;

}
