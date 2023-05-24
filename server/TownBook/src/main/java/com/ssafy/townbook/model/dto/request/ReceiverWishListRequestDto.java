package hide information.townbook.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiverWishListRequestDto {
    private Long accountNo;

    private Long bookLogNo;

    public ReceiverWishListRequestDto(Long accountNo, Long bookLogNo) {
        this.accountNo = accountNo;
        this.bookLogNo = bookLogNo;
    }
}
