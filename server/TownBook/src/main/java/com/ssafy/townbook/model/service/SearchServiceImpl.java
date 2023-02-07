package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.BookLogDto;
import com.ssafy.townbook.model.entity.BookLog;
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
    
    @Autowired
    public SearchServiceImpl(SearchQueryRepository searchQueryRepository) {
        this.searchQueryRepository = searchQueryRepository;
    }
    
    /**
     * 제목 검색해서 북로그 반환
     *
     * @param bookTitle
     * @return List<BookLogDto>
     */
    @Override
    public List<BookLogDto> findBookLogByBookTitle(String bookTitle) {
        List<BookLog> findBookLogByBookTitle = searchQueryRepository.findBookLogByBookTitle(bookTitle).get();
        return findBookLogByBookTitle.stream()
                .map(BookLogDto::new)
                .collect(Collectors.toList());
    }
}
