package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    
    private BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public List<BookDto> findAll() {
        List<Book> findBooks = bookRepository.findAll();
        return findBooks.stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public BookDto findBookByBookIsbn(String bookIsbn) {
        Book findBook = bookRepository.findBookByBookIsbn(bookIsbn);
        return new BookDto(findBook);
    }
    
    @Override
    @Transactional
    public void addBook(String bookIsbn) {
        try {
            // API Key, URL 설정 - 나중에 API Key 외부로 이동
            String APIKey = "728b82e7aa453ba85bfe1dfa1680c4fd825c92d97e380b2ceb360d127020afe7";
            URL url = new URL("https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" +
                    APIKey + "&result_style=json&page_no=1&page_size=10&isbn=" + bookIsbn);
            
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
            System.out.println(book);
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println("정보가 없는 도서입니다");
        }
    }
}
