package com.ssafy.townbook.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.dto.response.FindOneResponseDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.WishList;
import com.ssafy.townbook.model.repository.AccountRepository;
import com.ssafy.townbook.model.repository.BookRepository;
import com.ssafy.townbook.queryrepository.MyPageQueryRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MyPageServiceImpl implements MyPageService {
    
    private MyPageQueryRepository myPageQueryRepository;
    private AccountRepository     accountRepository;
    private BookRepository        bookRepository;
    
    @Autowired
    public MyPageServiceImpl(MyPageQueryRepository myPageQueryRepository, AccountRepository accountRepository,
            BookRepository bookRepository) {
        this.myPageQueryRepository = myPageQueryRepository;
        this.accountRepository     = accountRepository;
        this.bookRepository        = bookRepository;
    }
    
    /**
     * qr 코드 생성 및 반환
     *
     * @param qrSource
     * @return Optional<Object>
     * @throws WriterException
     * @throws IOException
     */
    @Override
    public Optional<Object> getQrCode(String qrSource) throws WriterException, IOException {
        int       width  = 200;
        int       height = 200;
        BitMatrix matrix = new MultiFormatWriter().encode(qrSource, BarcodeFormat.QR_CODE, width, height);
        
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
    public FindOneResponseDto findPointByAccountNo(Long accountNo) throws Exception {
        return new FindOneResponseDto(accountRepository.findByAccountNo(accountNo).get().getAccountPoint());
    }
    
    /**
     * 로그인 유저의 책 기부/수령 전체 로그와 책 정보 반환
     *
     * @param accountNo
     * @return JSONArray
     * @throws Exception
     */
    @Override
    public FindListResponseDto findBookLogByAccountNo(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.findBookLogByAccountNo(accountNo).get()) {
            Book       book       = bookRepository.findBookByBookIsbn(bookLog.getBook().getBookIsbn()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookNo", book.getBookIsbn());
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookLogLocker", bookLog.getLocker().getLockerNo());
            jsonObject.put("bookLogDonateDateTime", bookLog.getBookLogDonateDateTime());
            jsonArray.add(jsonObject);
        }
        return new FindListResponseDto(jsonArray);
    }
    
    /**
     * 로그인 유저의 기부 목록 반환
     *
     * @param accountNo
     * @return JSONArray
     * @throws Exception
     */
    @Override
    public FindListResponseDto findDonateBookLogByAccountNo(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.findDonateBookLogByAccountNo(accountNo).get()) {
            Book       book       = bookRepository.findBookByBookIsbn(bookLog.getBook().getBookIsbn()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookNo", book.getBookIsbn());
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookLogLocker", bookLog.getLocker().getLockerNo());
            jsonObject.put("bookLogDonateDateTime", bookLog.getBookLogDonateDateTime());
            jsonArray.add(jsonObject);
        }
        return new FindListResponseDto(jsonArray);
    }
    
    /**
     * 로그인 유저의 수령 목록 반환
     *
     * @param receiverNo
     * @return JSONArray
     * @throws Exception
     */
    @Override
    public FindListResponseDto findReceiveBookLogByReceiverNo(Long receiverNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (BookLog bookLog :
                myPageQueryRepository.findReceiveBookLogByReceiverNo(receiverNo).get()) {
            Book       book       = bookRepository.findBookByBookIsbn(bookLog.getBook().getBookIsbn()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookNo", book.getBookIsbn());
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookLogLocker", bookLog.getLocker().getLockerNo());
            jsonObject.put("bookLogDonateDateTime", bookLog.getBookLogDonateDateTime());
            jsonArray.add(jsonObject);
        }
        return new FindListResponseDto(jsonArray);
    }
    
    /**
     * 로그인 유저의 책 찜 목록 반환
     *
     * @param accountNo
     * @return JSONArray
     * @throws Exception
     */
    @Override
    public FindListResponseDto findWishListByAccountNo(Long accountNo) throws Exception {
        JSONArray jsonArray = new JSONArray();
        for (WishList wishList : myPageQueryRepository.findWishList(accountNo).get()) {
            BookLog bookLog = myPageQueryRepository.findBookLogByBookLogNo(wishList.getBookLog().getBookLogNo()).get();
            Book    book    = bookRepository.findBookByBookIsbn(bookLog.getBook().getBookIsbn()).get();
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookLogNo", bookLog.getBookLogNo());
            jsonObject.put("bookTitle", book.getBookTitle());
            jsonObject.put("bookTitleURL", book.getBookTitleURL());
            jsonObject.put("bookLogState", bookLog.getBookLogState());
            
            jsonArray.add(jsonObject);
            
        }
        return new FindListResponseDto(jsonArray);
    }
}
