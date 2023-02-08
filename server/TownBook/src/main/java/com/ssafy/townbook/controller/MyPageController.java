package com.ssafy.townbook.controller;

import com.google.zxing.WriterException;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.service.MyPageService;
import com.ssafy.townbook.model.service.MyPageServiceImpl;
import java.io.IOException;
import net.minidev.json.JSONArray;
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
    
    private MyPageService myPageService;
    
    @Autowired
    public MyPageController(MyPageServiceImpl myPageService) {
        this.myPageService = myPageService;
    }
    
    /**
     * 로그인 유저의 이메일로 만든 QR코드 반환
     *
     * @param qrSource
     * @return Optional<Object>
     * @throws WriterException
     * @throws IOException
     */
    @GetMapping("/qr/{qrSource}")
    public Object createQr(@PathVariable String qrSource) throws WriterException, IOException {
        return myPageService.getQrCode(qrSource).get();
    }
    
    
    /**
     * 로그인 유저의 포인트 반환
     *
     * @param accountNo
     * @return Optional<Integer>
     * @throws Exception
     */
    @GetMapping("/myPoint/{accountNo}")
    public ResponseEntity<FindOneResponseDto> findPointByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<FindOneResponseDto>(myPageService.findPointByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 기부/수령 전체 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/log/{accountNo}")
    public ResponseEntity<FindListResponseDto> findBookLogByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<FindListResponseDto>(myPageService.findBookLogByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 기부 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/donate/{accountNo}")
    public ResponseEntity<FindListResponseDto> findDonateBookLogByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<FindListResponseDto>(myPageService.findDonateBookLogByAccountNo(accountNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 수령 목록 반환
     *
     * @param receiverNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/receive/{receiverNo}")
    public ResponseEntity<FindListResponseDto> findReceiveBookLogByReceiverNo(@PathVariable Long receiverNo) throws Exception {
        return new ResponseEntity<FindListResponseDto>(myPageService.findReceiveBookLogByReceiverNo(receiverNo), HttpStatus.OK);
    }
    
    /**
     * 로그인 유저의 책 찜 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @GetMapping("/wishList/{accountNo}")
    public ResponseEntity<FindListResponseDto> findWishListByAccountNo(@PathVariable Long accountNo) throws Exception {
        return new ResponseEntity<FindListResponseDto>(myPageService.findWishListByAccountNo(accountNo), HttpStatus.OK);
    }
}