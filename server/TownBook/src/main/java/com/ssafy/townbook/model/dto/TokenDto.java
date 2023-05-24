package hide information.townbook.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    private String token;

    private AccountDto accountDto;

    @Builder
    public TokenDto(AccountDto accountDto, String token) {
        this.accountDto = accountDto;
        this.token = token;
    }
}
