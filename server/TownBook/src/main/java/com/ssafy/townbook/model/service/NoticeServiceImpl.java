package hide information.townbook.model.service;

import hide information.townbook.model.dto.NoticeDto;
import hide information.townbook.model.dto.request.ModifyNoticeRequestDto;
import hide information.townbook.model.dto.request.WriteNoticeRequestDto;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.dto.response.SaveOneResponseDto;
import hide information.townbook.model.entity.Notice;
import hide information.townbook.model.repository.NoticeRepository;
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
     * 공지 하나 가져오기
     *
     * @param noticeNo
     * @return NoticeDto
     */
    @Override
    public FindOneResponseDto findNoticeByNoticeNo(Long noticeNo) {
        Notice    notice    = noticeRepository.findById(noticeNo).get();
        NoticeDto noticeDto = new NoticeDto(notice);
        return new FindOneResponseDto<NoticeDto>(noticeDto);
    }
    
    /**
     * 공지사항/이용안내 전체 조회
     *
     * @return List<NoticeDto>
     */
    @Override
    public FindListResponseDto findAllNotice() {
        List<Notice> findNotice = noticeRepository.findAllByNoticeState(true).get();
        List<NoticeDto> findNoticeDto = findNotice.stream()
                .map(NoticeDto::new)
                .collect(Collectors.toList());
        return new FindListResponseDto(findNoticeDto);
    }
    
    /**
     * 카테고리(공지사항, 이용안내) 별로 최신 8개 리스트 가져오기
     *
     * @param category
     * @return List<NoticeDto>
     */
    @Override
    public FindListResponseDto findNoticeByNoticeStateAndNoticeCategoryOrderByNoticeNo(Integer category) {
        List<Notice> noticeList = noticeRepository.findByNoticeStateAndNoticeCategoryOrderByNoticeNo(true, category)
                .get();
        return new FindListResponseDto(noticeList.stream()
                .map(NoticeDto::new)
                .collect(Collectors.toList()));
    }
    
    /**
     * 공지사항 수정
     *
     * @param modifyNoticeRequestDto
     * @return Boolean
     */
    @Override
    @Transactional
    public SaveOneResponseDto modifyNotice(ModifyNoticeRequestDto modifyNoticeRequestDto) {
        try {
            long   noticeNo      = modifyNoticeRequestDto.getNoticeNo();
            String noticeTitle   = modifyNoticeRequestDto.getNoticeTitle();
            String noticeContent = modifyNoticeRequestDto.getNoticeContent();
            
            Notice notice = noticeRepository.findById(noticeNo).get();
            notice.setNoticeTitle(noticeTitle);
            notice.setNoticeContent(noticeContent);
            noticeRepository.save(notice);
            return new SaveOneResponseDto(true);
        } catch (Exception e) {
            e.getMessage();
            return new SaveOneResponseDto(false);
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
    public SaveOneResponseDto writeNotice(WriteNoticeRequestDto writeNoticeRequestDto) {
        try {
            Notice notice = new Notice(writeNoticeRequestDto);
            noticeRepository.save(notice);
            return new SaveOneResponseDto(true);
        } catch (Exception e) {
            return new SaveOneResponseDto(e.getMessage());
        }
    }
    
    /**
     * 공지사항 삭제
     *
     * @param noticeNo
     * @return Boolean
     */
    @Override
    @Transactional
    public SaveOneResponseDto removeNotice(Long noticeNo) {
        try {
            Notice notice = noticeRepository.findById(noticeNo).get();
            notice.setNoticeState(false);
            noticeRepository.save(notice);
            return new SaveOneResponseDto(true);
        } catch (Exception e) {
            return new SaveOneResponseDto(e.getMessage());
        }
    }
}
