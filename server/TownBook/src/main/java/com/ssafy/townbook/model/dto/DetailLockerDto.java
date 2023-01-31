package com.ssafy.townbook.model.dto;

import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailLockerDto {
    
    private Long detailLockerNo;
    private BookLog bookLog;
    private Boolean detailLockerIsEmpty;
    
    public DetailLockerDto(DetailLocker detailLocker) {
        
        this.detailLockerNo = detailLocker.getDetailLockerNo();
        this.bookLog = detailLocker.getBookLog();
        this.detailLockerIsEmpty = detailLocker.getDetailLockerIsEmpty();
    }
}
