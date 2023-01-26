package com.ssafy.townbook.model.service;

import com.google.zxing.WriterException;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Optional;

public interface MyPageService {

    Object getQrCode(String accountEmail) throws WriterException, IOException;

    int getPoint(Long accountNo) throws Exception;

    Optional<JSONArray> getAllLog(Long accountNo) throws Exception;

    Optional<JSONArray> getDonateLog(Long accountNo) throws Exception;

    Optional<JSONArray> getReceiveLog(Long receiverNo) throws Exception;

    Optional<JSONArray> getWishList(Long accountNo) throws Exception;
}
