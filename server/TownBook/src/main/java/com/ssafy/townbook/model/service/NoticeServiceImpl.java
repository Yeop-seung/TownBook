package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.dto.request.ModifyNoticeRequestDto;
import com.ssafy.townbook.model.dto.request.WriteNoticeRequestDto;
import com.ssafy.townbook.model.entity.Notice;
import com.ssafy.townbook.model.repository.NoticeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {
    
    private NoticeRepository noticeRepository;
    
    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    
    /**
     * 공지사항 수정
     *
     * @param modifyNoticeRequestDto
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto) {
        
        try {
            Notice notice = new Notice(modifyNoticeRequestDto);
            Notice modifyNotice = noticeRepository.findById(notice.getNoticeNo()).get();
            modifyNotice.setNoticeContent(notice.getNoticeContent());
            modifyNotice.setNoticeTitle(notice.getNoticeTitle());
            noticeRepository.save(modifyNotice);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
    
    /**
     * 공지사항 작성
     *
     * @param writeNoticeRequestDto
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean writeNotice(WriteNoticeRequestDto writeNoticeRequestDto) {
        try {
            Notice notice = new Notice(writeNoticeRequestDto);
            noticeRepository.save(notice);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
    
    /**
     * 공지사항 or 이용안내 가져오기
     *
     * @return List<NoticeDto>
     */
    @Override
    public List<NoticeDto> findTop8ByNoticeStateAndNoticeCategoryOrderByNoticeNo(Integer category) {
        List<Notice> noticeList = noticeRepository.findTop8ByNoticeStateAndNoticeCategoryOrderByNoticeNo(true, category)
                .get();
        return noticeList.stream()
                .map(NoticeDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * 공지 하나 가져오기
     *
     * @param noticeNo
     * @return NoticeDto
     */
    @Override
    public NoticeDto getNotice(Long noticeNo) {
        Notice notice = noticeRepository.findById(noticeNo).get();
        return new NoticeDto(notice);
    }
    
    /**
     * 공지사항 삭제
     *
     * @param noticeNo
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean removeNotice(Long noticeNo) {
        try {
            Notice notice = noticeRepository.findById(noticeNo).get();
            notice.setNoticeState(false);
            noticeRepository.save(notice);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
