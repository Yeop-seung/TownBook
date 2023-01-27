package com.ssafy.townbook.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssafy.townbook.model.entity.Board;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.WishList;
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

    /**
     * qr 코드 생성 및 반환
     *
     * @param accountEmail
     * @return Optional<Object>
     * @throws WriterException
     * @throws IOException
     */
    @Override
    public Optional<Object> getQrCode(String accountEmail) throws WriterException, IOException {
        int width = 200;
        int height = 200;
        BitMatrix matrix = new MultiFormatWriter().encode(accountEmail, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            MatrixToImageWriter.writeToStream(matrix, "PNG", out);
            return Optional.ofNullable(ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray()));
        }
    }

    /**
     * 유저 번호를 통해 유저의 포인트 반환
     *
     * @param accountNo
     * @return Optional<Integer>
     * @throws Exception
     */
    @Override
    public Optional<Integer> getPoint(Long accountNo) throws Exception {
        return Optional.of(myPageQueryRepository.findAccount(accountNo).get().get(0).getAccountPoint());
    }

    /**
     * 로그인 유저의 책 기부/수령 전체 로그와 책 정보 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @Override
    public Optional<JSONArray> getAllLog(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();

        for (BookLog bookLog :
                myPageQueryRepository.findBookLogByAccountNo(accountNo).get()) {
            Book book = myPageQueryRepository.findBook(bookLog.getBook().getBookIsbn()).get();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookVol", book.getBookVol());
            jsonObject.put("bookLogDonateDate", bookLog.getBookLogDonateDate());

            jsonArray.add(jsonObject);
        }

        for (BookLog bookLog :
                myPageQueryRepository.findReceiveBookLog(accountNo).get()) {
            Book book = myPageQueryRepository.findBook(bookLog.getBook().getBookIsbn()).get();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookVol", book.getBookVol());
            jsonObject.put("bookLogReceiveDate", bookLog.getBookLogReceiveDate());

            jsonArray.add(jsonObject);
        }
        return Optional.ofNullable(jsonArray);
    }

    /**
     * 로그인 유저의 기부 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @Override
    public Optional<JSONArray> getDonateLog(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.findBookLogByAccountNo(accountNo).get()) {
            Book book = myPageQueryRepository.findBook(bookLog.getBook().getBookIsbn()).get();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookVol", book.getBookVol());
            jsonObject.put("bookLogDonateDate", bookLog.getBookLogDonateDate());

            jsonArray.add(jsonObject);
        }
        return Optional.ofNullable(jsonArray);
    }

    /**
     * 로그인 유저의 수령 목록 반환
     *
     * @param receiverNo
     * @return Optional<JSONArray>
     * @throws Exception
     * @throws Exception
     */
    @Override
    public Optional<JSONArray> getReceiveLog(Long receiverNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.findReceiveBookLog(receiverNo).get()) {
            Book book = myPageQueryRepository.findBook(bookLog.getBook().getBookIsbn()).get();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookVol", book.getBookVol());
            jsonObject.put("bookLogReceiveDate", bookLog.getBookLogReceiveDate());

            jsonArray.add(jsonObject);
        }
        return Optional.ofNullable(jsonArray);
    }

    /**
     * 로그인 유저의 책 찜 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @Override
    public Optional<JSONArray> getWishList(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (WishList wishList : myPageQueryRepository.findWishList(accountNo).get()
        ) {
            BookLog bookLog = myPageQueryRepository.findBookLogByBookLogNo(wishList.getBookLog().getBookLogNo()).get();
            Book book = myPageQueryRepository.findBook(bookLog.getBook().getBookIsbn()).get();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookLogNo", bookLog.getBookLogNo());
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookTitleURL", book.getBookTitleURL());
            jsonObject.put("bookLogState", bookLog.getBookLogState());

            jsonArray.add(jsonObject);

        }
        return Optional.ofNullable(jsonArray);
    }

    /**
     * 로그인 유저의 작성 게시글 목록 반환
     *
     * @param accountNo
     * @return Optional<JSONArray>
     * @throws Exception
     */
    @Override
    public Optional<JSONArray> getBoardList(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();

        for (Board board : myPageQueryRepository.findBoard(accountNo).get()
        ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("boardNo",board.getBoardNo());
            jsonObject.put("boardTitle",board.getBoardTitle());
            jsonObject.put("boardWriteDate",board.getBoardWriteDate());

            jsonArray.add(jsonObject);
        }
        return Optional.ofNullable(jsonArray);
    }
}
