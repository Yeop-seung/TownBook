package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.dto.LockerDto;
import com.ssafy.townbook.model.dto.response.FindListResponseDto;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.Locker;
import com.ssafy.townbook.model.repository.BookLogRepository;
import com.ssafy.townbook.model.repository.LockerRepository;
import com.ssafy.townbook.queryrepository.SearchQueryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    
    private SearchQueryRepository searchQueryRepository;
    private LockerRepository      lockerRepository;
    private BookLogRepository     bookLogRepository;
    
    @Autowired
    public SearchServiceImpl(SearchQueryRepository searchQueryRepository, LockerRepository lockerRepository,
            BookLogRepository bookLogRepository) {
        this.searchQueryRepository = searchQueryRepository;
        this.lockerRepository      = lockerRepository;
        this.bookLogRepository     = bookLogRepository;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @Override
    public FindListResponseDto findBookLogByBookTitle(String bookTitle) {
        List<BookLog> findBookLogByBookTitle = searchQueryRepository.findBookLogByBookTitle(bookTitle).get();
        return new FindListResponseDto(findBookLogByBookTitle.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList()));
    }
    
    @Override
    public FindListResponseDto findLockerByLockerNo(Long lockerNo) {
//        Locker locker = lockerRepository.findLockerByLockerNo(lockerNo).get();
//        BookLog bookLog = bookLogRepository.findBookLogByBookLogNo()
        return null;
    }
}
