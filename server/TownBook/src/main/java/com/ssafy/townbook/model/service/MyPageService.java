package com.ssafy.townbook.model.service;

import com.google.zxing.WriterException;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Optional;

public interface MyPageService {

    Optional<Object> getQrCode(String accountEmail) throws WriterException, IOException;

    Integer getPoint(Long accountNo) throws Exception;

    JSONArray getAllLog(Long accountNo) throws Exception;

    JSONArray getDonateLog(Long accountNo) throws Exception;

    JSONArray getReceiveLog(Long receiverNo) throws Exception;

    JSONArray getWishList(Long accountNo) throws Exception;

    JSONArray getBoardList(Long accountNo) throws Exception;
}
