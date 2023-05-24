package hide information.townbook.model.service;

import hide information.townbook.model.dto.BookDto;
import hide information.townbook.model.dto.BookLogDto;
import hide information.townbook.model.dto.LockerDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.entity.Book;
import hide information.townbook.model.entity.BookLog;
import hide information.townbook.model.repository.BookLogRepository;
import hide information.townbook.model.repository.BookRepository;
import hide information.townbook.model.repository.LockerRepository;
import hide information.townbook.queryrepository.BookLogQueryRepository;
import hide information.townbook.queryrepository.SearchQueryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    
    private SearchQueryRepository  searchQueryRepository;
    private BookRepository         bookRepository;
    private BookLogQueryRepository bookLogQueryRepository;
    private LockerRepository       lockerRepository;
    
    @Autowired
    public SearchServiceImpl(SearchQueryRepository searchQueryRepository, BookRepository bookRepository,
            BookLogQueryRepository bookLogQueryRepository, LockerRepository lockerRepository) {
        this.searchQueryRepository  = searchQueryRepository;
        this.bookRepository         = bookRepository;
        this.bookLogQueryRepository = bookLogQueryRepository;
        this.lockerRepository       = lockerRepository;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @Override
    public FindListResponseDto searchBookLogByBookTitle(String bookTitle) {
        List<BookLogDto> findBookLogByBookTitle = searchQueryRepository.findBookLogByBookTitle(bookTitle).get().stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList());
        JSONArray jsonArray = new JSONArray();
        
        for (BookLogDto bookLogDto : findBookLogByBookTitle) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookLog", bookLogDto);
            jsonObject.put("book", new BookDto(bookRepository.findBookByBookIsbn(bookLogDto.getBookIsbn()).get()));
            jsonArray.add(jsonObject);
        }
        return new FindListResponseDto(jsonArray);
    }
    
    /**
     * 보관함 검색
     *
     * @param lockerNo
     * @return
     */
    @Override
    public FindListResponseDto searchLockerByLockerNo(Long lockerNo) {
        LockerDto     lockerDto   = new LockerDto(lockerRepository.findLockerByLockerNo(lockerNo).get());
        List<BookLog> bookLogList = bookLogQueryRepository.findBookLogByLockerNo(lockerNo).get();
        List<BookLogDto> bookLogDtoList = bookLogList.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList());
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Locker", lockerDto);
        JSONArray jsonArray = new JSONArray();
        for (BookLogDto bookLogDto : bookLogDtoList) {
            JSONObject jsonObject1 = new JSONObject();
            
            // bookTitle
            Book book = bookRepository.findBookByBookIsbn(bookLogDto.getBookIsbn()).get();
            jsonObject1.put("bookTitle", book.getBookTitle());
            
            jsonObject1.put("detailLockerNo", bookLogDto.getLockerNo());
            jsonObject1.put("detailLockerNoInLocker", bookLogDto.getDetailLockerNo());
            jsonObject1.put("bookIntroductionURL", book.getBookIntroductionURL());
            jsonObject1.put("bookTitleURL", book.getBookTitleURL());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("BookLogDtoList", jsonArray);
        List<JSONObject> result = new ArrayList<>();
        result.add(jsonObject);
        return new FindListResponseDto(result);
    }
}
