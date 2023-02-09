package com.ssafy.townbook.model.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SaveOneResponseDto<T> {

    private Boolean success;
    private String  message;
    private T       data;

    @Builder
    public SaveOneResponseDto(T data) {
            this.success = true;
            this.message = "저장 성공";
            this.data = data;
    }

    @Builder
    public SaveOneResponseDto() {
        this.success = false;
        this.message = "저장 실패";
    }
}
