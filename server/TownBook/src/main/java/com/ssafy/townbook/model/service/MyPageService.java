package com.ssafy.townbook.model.service;

import com.google.zxing.WriterException;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Optional;

public interface MyPageService {

    Optional<Object> getQrCode(String accountEmail) throws WriterException, IOException;

    Integer findPointByAccountNo(Long accountNo) throws Exception;

    JSONArray findBookLogByAccountNo(Long accountNo) throws Exception;

    JSONArray findBookLogDonateByAccountNo(Long accountNo) throws Exception;

    JSONArray findBookLogReceiverByReceiverNo(Long receiverNo) throws Exception;

    JSONArray findWishListByAccountNo(Long accountNo) throws Exception;

}
