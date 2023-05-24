package hide information.townbook.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String accountEmail;
    
    @NotNull
    @Size(min = 3, max = 100)
    private String accountPw;
    
    @Builder
    public LoginDto(String accountEmail, String accountPw) {
        this.accountEmail = accountEmail;
        this.accountPw    = accountPw;
    }
}
