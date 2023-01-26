package com.ssafy.townbook.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.repository.MyPageRepository;
import com.ssafy.townbook.queryrepository.MyPageQueryRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


@Service
public class MyPageServiceImpl implements MyPageService {
    @Autowired
    private MyPageQueryRepository myPageQueryRepository;

    @Autowired
    private MyPageRepository myPageRepository;

    /**
     * qr 코드 생성 및 반환
     *
     * @param accountEmail
     * @return Object
     * @throws WriterException
     * @throws IOException
     */
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

    /**
     * 유저 번호를 통해 유저의 포인트 반환
     *
     * @param accountNo
     * @return int
     * @throws Exception
     */
    @Override
    public int getPoint(Long accountNo) throws Exception {
        return myPageQueryRepository.findByName(accountNo).get(0).getAccountPoint();
    }

    @Override
    public Optional<JSONArray> getAllLog(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.getBookLog(accountNo).get()) {
            Book book = myPageQueryRepository.getBook(bookLog.getBook().getBookIsbn()).get().get(0);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookVol",book.getBookVol());
            jsonObject.put("bookLogDonateDate", bookLog.getBookDonateDate());
            jsonObject.put("bookLogReceiveDate", bookLog.getBookReceiveDate());

            jsonArray.add(jsonObject);
        }


        return Optional.ofNullable(jsonArray);
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
