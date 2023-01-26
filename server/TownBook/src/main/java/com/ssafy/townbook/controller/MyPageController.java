package com.ssafy.townbook.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.service.MyPageService;
import com.ssafy.townbook.model.service.MyPageServiceImpl;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/myPage")
public class MyPageController {

    @Autowired
    private MyPageServiceImpl myPageService;

    @GetMapping("/qr/{accountEmail}")
    public ResponseEntity<?> createQr(@PathVariable String accountEmail) throws WriterException, IOException {
        return new ResponseEntity<>(myPageService.getQrCode(accountEmail), HttpStatus.OK);
    }

    @GetMapping("/myPoint/{accountNo}")
    public ResponseEntity<?> getPoint(@PathVariable Long accountNo) throws Exception{
        return new ResponseEntity<>(myPageService.getPoint(accountNo),HttpStatus.OK);
    }

    @GetMapping("/allLog/{accountNo}")
    public ResponseEntity<Optional<JSONArray>> getAllLog(@PathVariable Long accountNo) throws Exception{
        return new ResponseEntity<>(myPageService.getAllLog(accountNo),HttpStatus.OK);
    }

//    @GetMapping("/donate/{accountNo}")
//    public ResponseEntity<?> getDonateLog(@PathVariable Long accountNo) throws Exception{
//        return new ResponseEntity<>(null,HttpStatus.OK);
//    }
//
//    @GetMapping("/receive/{accountNo}")
//    public ResponseEntity<?> getReceiveLog(@PathVariable Long accountNo) throws Exception{
//        return new ResponseEntity<>(null,HttpStatus.OK);
//    }
}