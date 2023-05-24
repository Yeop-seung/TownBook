package hide information.townbook.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDto {
    
    private String authorityName;
    
    @Builder
    public AuthorityDto(String authorityName) {
        this.authorityName = authorityName;
    }
}
