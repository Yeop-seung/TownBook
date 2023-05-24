package hide information.townbook.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveBookLogResponseDto {
    
    private Integer accountPoint;
    private String  status;
    
    @Builder
    public ReceiveBookLogResponseDto(Integer accountPoint) {
        this.accountPoint = accountPoint;
    }
    
    @Builder
    public ReceiveBookLogResponseDto(String status) {
        this.status = status;
    }
}
