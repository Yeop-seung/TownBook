package hide information.townbook.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLockerRequestDto {
    
    private String  lockerRegion;
    private Integer detailLockerCount;
    private Double  lockerLatitude;
    private Double  lockerLongitude;
    
    @Builder
    public CreateLockerRequestDto(String lockerRegion, Integer detailLockerCount, Double lockerLatitude,
            Double lockerLongitude) {
        this.lockerRegion      = lockerRegion;
        this.detailLockerCount = detailLockerCount;
        this.lockerLatitude    = lockerLatitude;
        this.lockerLongitude   = lockerLongitude;
    }
}
