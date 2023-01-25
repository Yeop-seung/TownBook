package com.ssafy.townbook.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.MyPageRepository;
import com.ssafy.townbook.queryrepository.MyPageQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class MyPageServiceImpl implements MyPageService {
    @Autowired
    private MyPageQueryRepository myPageQueryRepository;

    @Autowired
    private MyPageRepository myPageRepository;

    @Override
    public Object getQrCode(String accountEmail) throws WriterException, IOException {
        int width = 200;
        int height = 200;
        BitMatrix matrix = new MultiFormatWriter().encode(accountEmail, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            MatrixToImageWriter.writeToStream(matrix, "PNG", out);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray());
        }
    }

    @Override
    public int getPoint(Long accountNo) throws Exception{
        return myPageQueryRepository.findByName(accountNo).get(0).getAccountPoint();
    }

    @Override
    public Optional<List<BookLog>> getAllLog(Long accountNo) throws Exception {
        return myPageRepository.findByAccount_AccountNo(accountNo);
    }

//    @Override
//    public List<BookLog> getDonateLog(Long accountNo) throws Exception {
//        return null;
//    }
//
//    @Override
//    public List<BookLog> getReceiveLog(Long accountNo) throws Exception {
//        return null;
//    }
}
