package com.ssafy.townbook.model.dto.request;

import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.DetailLocker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceiveBookRequestDto {
    
    private BookLog bookLog;
    private DetailLocker detailLocker;
    private Account account;
    
    public ReceiveBookRequestDto(BookLog bookLog, DetailLocker detailLocker, Account account) {
        this.bookLog = bookLog;
        this.detailLocker = detailLocker;
        this.account = account;
    }
}
