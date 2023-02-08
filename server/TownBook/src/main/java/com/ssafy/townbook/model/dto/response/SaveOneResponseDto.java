package com.ssafy.townbook.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveOneResponseDto<T> {

    private Boolean success;
    private String  message;
    private T       data;

    @Builder
    public SaveOneResponseDto(T data) {
        if((Boolean)data == true) {
            this.success = true;
            this.message = "저장 성공";
            this.data = data;
        }
        else {
            this.success = false;
            this.message = "저장 실패";
        }
    }

    @Builder
    public SaveOneResponseDto() {
        this.success = false;
        this.message = "저장 실패";
    }
}
