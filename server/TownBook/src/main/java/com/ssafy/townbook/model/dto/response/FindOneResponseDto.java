package hide information.townbook.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindOneResponseDto<T> {
    
    private Boolean success;
    private String  message;
    private T       data;
    
    @Builder
    public FindOneResponseDto(T data) {
        this.success = true;
        this.message = "조회 성공";
        this.data    = data;
    }
    
    @Builder
    public FindOneResponseDto() {
        this.success = false;
        this.message = "조회 실패";
    }
}
