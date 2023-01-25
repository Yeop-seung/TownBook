package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    
    private final BookRepository bookRepository;
    
    /**
     * 전체 책 조회
     * ADMIN 권한 확인 후 작동 해야함
     *
     * @return List
     */
    @GetMapping("/book")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Book> books() {
        return bookRepository.findAll();
    }
    
    /**
     * 도서의 ISBN 으로 도서 조회
     * 권한은 아무나? 일단 보류
     *
     * @param bookIsbn
     * @return Book
     */
    @GetMapping("/book/{bookIsbn}")
    public Book findBookByBookIsbn(@PathVariable String bookIsbn) {
        return bookRepository.findBookByBookIsbn(bookIsbn);
    }
    
    /**
     * 도서 추가
     * V1 : 임의 도서 추가
     * V2 : 바코드로 읽은 ISBN 도서 API 호출
     * V3 : 호출한 도서 추가
     */
    @PostMapping("/book/add/{bookIsbn}")
    public void add(@PathVariable String bookIsbn) {
        try {
            // API Key, URL 설정 - 나중에 API Key 외부로 이동
            String requestBookIsbn = bookIsbn;
            String APIKey = "728b82e7aa453ba85bfe1dfa1680c4fd825c92d97e380b2ceb360d127020afe7";
            URL url = new URL("https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" +
                    APIKey + "&result_style=json&page_no=1&page_size=10&isbn=" + requestBookIsbn);
            
            // 도서 정보를 담은 jsonObject 가공
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = br.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray jsonArray = (JSONArray) jsonObject.get("docs");
            jsonObject = (JSONObject) jsonArray.get(0);
            
            // 도서 저장 - VOL 정수 처리, 6자리 날짜 localDate 변환
            Book book = new Book();
            book.setBookIsbn((String) jsonObject.get("EA_ISBN"));
            book.setBookSubject((String) jsonObject.get("SUBJECT"));
            book.setBookTitle((String) jsonObject.get("TITLE"));
//            book.setBookVol((Integer) jsonObject.get("VOL"));
            book.setBookVol(0);
            book.setBookAuthor((String) jsonObject.get("AUTHOR"));
            book.setBookPublisher((String) jsonObject.get("PUBLISHER"));
//            book.setBookPublishPredate(LocalDate.now());
            book.setBookPublishPredate(LocalDate.now());
            book.setBookIntroductionURL((String) jsonObject.get("BOOK_INTRODUCTION_URL"));
            book.setBookTitleURL((String) jsonObject.get("TITLE_URL"));
            bookRepository.save(book);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
