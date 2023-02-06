package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookDto;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.repository.BookRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    
    @Value("${APIKey}")
    private String APIKey;
    
    private BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    /**
     * 전체 책 조회
     *
     * @return List<BookDto>
     */
    @Override
    public List<BookDto> findAll() {
        Optional<List<Book>> findBooks = Optional.ofNullable(bookRepository.findAll());
        return findBooks.get().stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * ISBN 으로 도서 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    @Override
    public BookDto findBookByBookIsbn(String bookIsbn) {
        Book findBook = bookRepository.findBookByBookIsbn(bookIsbn).get();
        return new BookDto(findBook);
    }
    
    
    /**
     * ISBN으로 국립도서관의 도서 정보 조회
     *
     * @param bookIsbn
     * @return BookDto
     */
    @Override
    @Transactional
    public BookDto addBook(String bookIsbn) {
        try {
            // API 호출
            URL url = new URL("https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" +
                    APIKey + "&result_style=json&page_no=1&page_size=10&isbn=" + bookIsbn);
            
            // Json 가공
            BufferedReader br         = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String         result     = br.readLine();
            JSONParser     jsonParser = new JSONParser();
            JSONObject     jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray      jsonArray  = (JSONArray) jsonObject.get("docs");
            jsonObject = (JSONObject) jsonArray.get(0);
            
            // Json -> Book 주입
            Book book = new Book();
            book.setBookIsbn((String) jsonObject.get("EA_ISBN"));
            book.setBookSubject((String) jsonObject.get("SUBJECT"));
            book.setBookTitle((String) jsonObject.get("TITLE"));
            book.setBookVol(checkVol((String) jsonObject.get("VOL")));
            book.setBookAuthor((String) jsonObject.get("AUTHOR"));
            book.setBookPublisher((String) jsonObject.get("PUBLISHER"));
            book.setBookPublishPredate(convertDate((String) jsonObject.get("PUBLISH_PREDATE")));
            book.setBookIntroductionURL(jsonObject.get("BOOK_INTRODUCTION_URL").equals("") ? "null.png"
                    : (String) jsonObject.get("BOOK_INTRODUCTION_URL"));
            book.setBookTitleURL((String) jsonObject.get("TITLE_URL"));
            
            System.out.println("저장 전");
            System.out.println("book = " + book);
            bookRepository.save(book);
            System.out.println("저장 후");
            return new BookDto(book);
        } catch (Exception e) {
            System.out.println("정보가 없는 도서입니다");
            return null;
        }
    }
    
    /**
     * vol이 null인지 체크
     * 정수로 반환
     *
     * @param volString
     * @return volInteger
     */
    static Integer checkVol(String volString) {
        return volString.equals("") ? 0 : Integer.parseInt(volString);
    }
    
    /**
     * 문자열 -> LocalDate 변환
     *
     * @param dateString
     * @return LocalDate
     */
    static LocalDate convertDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate;
    }
}
