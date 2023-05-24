package hide information.townbook.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateBookLogResponseDto {
    
    private Integer accountPoint;
    private String  status;
    
    @Builder
    public DonateBookLogResponseDto(Integer accountPoint) {
        this.accountPoint = accountPoint;
    }
    
    @Builder
    public DonateBookLogResponseDto(String status) {
        this.status = status;
    }
}
