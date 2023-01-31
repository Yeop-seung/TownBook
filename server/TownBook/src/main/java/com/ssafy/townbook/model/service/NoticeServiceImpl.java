package com.ssafy.townbook.model.service;

import com.ssafy.townbook.model.dto.NoticeDto;
import com.ssafy.townbook.model.entity.Notice;
import com.ssafy.townbook.model.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.List;
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
     * @param notice
     * @return
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
     * @param notice
     * @return
     */
    @Override
    public Boolean writeNotice(NoticeDto noticeDto) {
        try{
            Notice notice = new Notice();
            notice.setNoticeViews(noticeDto.getNoticeViews());
            notice.setNoticeCategory(noticeDto.getNoticeCategory());
            notice.setNoticeTitle(notice.getNoticeTitle());
            notice.setNoticeContent(notice.getNoticeContent());
            notice.setNoticeWriteDateTime(LocalDateTime.now());
            notice.setAccount(noticeDto.get);
            noticeRepository.save(notice);
            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    /**
     * 공지사항 리스트 가져오기
     * @return
     */
    @Override
    public List<Notice> getNoticeList() {
        List<Notice> noticeList = noticeRepository.findTop8ByNoticeStatusByOrderByNoticeNo(true).get();
        return noticeList;
    }

    /**
     * 공지 하나 가져오기
     * @param noticeNo
     * @return
     */
    @Override
    public Notice getNotice(Long noticeNo){
        Notice notice = noticeRepository.findById(noticeNo).get();
        return notice;
    }

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
