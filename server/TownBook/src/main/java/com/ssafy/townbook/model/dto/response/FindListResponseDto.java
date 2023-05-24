package hide information.townbook.model.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindListResponseDto {
    
    private Boolean success;
    private String  message;
    private Integer count;
    private List<?> data;
    
    @Builder
    public FindListResponseDto(List<?> data) {
        this.success = true;
        this.message = "조회 성공";
        this.count   = data.size();
        this.data    = data;
    }
    
    @Builder
    public FindListResponseDto() {
        this.success = false;
        this.message = "조회 실패";
    }
}
