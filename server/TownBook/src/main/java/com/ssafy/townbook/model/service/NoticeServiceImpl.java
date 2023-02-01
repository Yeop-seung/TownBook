package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.entity.Notice;
import com.ssafy.townbook.model.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 공지사항 수정
     *
     * @param notice
     * @return Boolean
     */
    @Override
    public Boolean modifyNotice(Notice notice) {
        try{
            Notice modifyNotice = noticeRepository.findById(notice.getNoticeNo()).get();
            modifyNotice.setNoticeContent(notice.getNoticeContent());
            modifyNotice.setNoticeTitle(notice.getNoticeTitle());
            LocalDateTime currDateTime = LocalDateTime.now();
            modifyNotice.setNoticeWriteDateTime(currDateTime);
            noticeRepository.save(modifyNotice);

            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    /**
     * 공지사항 작성
     *
     * @param notice
     * @return Boolean
     */
    @Override
    public Boolean writeNotice(Notice notice) {
        try{
            noticeRepository.save(notice);
            return true;
        }catch (Exception e){
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
    public List<NoticeDto> getNoticeList(Integer category) {
        List<Notice> noticeList = noticeRepository.findTop8ByNoticeStateAndNoticeCategory(true, category).get();
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
    public NoticeDto getNotice(Long noticeNo){
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
    public Boolean removeNotice(Long noticeNo) {
        try {
            Notice notice = noticeRepository.findById(noticeNo).get();
            notice.setNoticeState(false);
            noticeRepository.save(notice);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
