package com.ssafy.townbook.controller;

import com.google.zxing.WriterException;
import com.ssafy.townbook.model.service.MyPageServiceImpl;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 유저의 마이페이지 컨트롤러
 */
@RestController
@RequestMapping("/myPage")
public class MyPageController {
    
    @Autowired
    private MyPageServiceImpl myPageService;
    
    /**
     * 로그인 유저의 이메일로 만든 QR코드 반환
     *
     * @param accountEmail
     * @return Optional<Object>
     * @throws WriterException
     * @throws IOException
     */
    @GetMapping("/qr/{accountEmail}")
    public Object createQr(@PathVariable String accountEmail) throws WriterException, IOException {
        return myPageService.getQrCode(accountEmail).get();
    }

    
    /**
     * 로그인 유저의 포인트 반환
     *
     * @param accountNo
     * @return Optional<Integer>
     * @throws Exception
     */
    @GetMapping("/myPoint/{accountNo}")
    public ResponseEntity<?> findPointByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<>(myPageService.findPointByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 기부/수령 전체 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/allLog/{accountNo}")
    public ResponseEntity<?> findBookLogByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<>(myPageService.findBookLogByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 기부 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/donate/{accountNo}")
    public ResponseEntity<?> findBookLogDonateByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<>(myPageService.findBookLogDonateByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 수령 목록 반환
     *
     * @param receiverNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/receive/{receiverNo}")
    public ResponseEntity<?> findBookLogReceiverByReceiverNo(@PathVariable Long receiverNo) throws Exception {
        return new ResponseEntity<>(myPageService.findBookLogReceiverByReceiverNo(receiverNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 책 찜 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/wishList/{accountNo}")
    public ResponseEntity<?> findWishListByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<>(myPageService.findWishListByAccountNo(accountNo), HttpStatus.OK);
    }
}