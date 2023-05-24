package hide information.townbook.model.repository;

import hide information.townbook.model.entity.Notice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
    Optional<List<Notice>> findByNoticeStateAndNoticeCategoryOrderByNoticeNo(Boolean noticeState, Integer category);
    
    Optional<List<Notice>> findAllByNoticeState(Boolean noticeState);
}
