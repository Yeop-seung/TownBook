package com.ssafy.townbook.model.service;

import com.google.zxing.WriterException;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Optional;

public interface MyPageService {

    Optional<Object> getQrCode(String accountEmail) throws WriterException, IOException;

    Optional<Integer> getPoint(Long accountNo) throws Exception;

    Optional<JSONArray> getAllLog(Long accountNo) throws Exception;

    Optional<JSONArray> getDonateLog(Long accountNo) throws Exception;

    Optional<JSONArray> getReceiveLog(Long receiverNo) throws Exception;

    Optional<JSONArray> getWishList(Long accountNo) throws Exception;

    Optional<JSONArray> getBoardList(Long accountNo) throws Exception;
}
