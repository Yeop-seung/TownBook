package hide information.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveBookRequestDto {
    
    private Long   bookLogNo;
    private Long   accountNo;
    private String bookIsbn;
    private Long   lockerNo;
    private Long   detailLockerNo;
    
    @Builder
    public ReceiveBookRequestDto(Long bookLogNo, Long accountNo, String bookIsbn, Long lockerNo, Long detailLockerNo) {
        this.bookLogNo      = bookLogNo;
        this.accountNo      = accountNo;
        this.bookIsbn       = bookIsbn;
        this.lockerNo       = lockerNo;
        this.detailLockerNo = detailLockerNo;
    }
}
