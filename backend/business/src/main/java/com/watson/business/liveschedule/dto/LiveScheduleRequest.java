package com.watson.business.liveschedule.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveScheduleRequest {
    @NotNull
    private String realtorId;
    @NotNull
    private Long houseId;
    @NotNull
    private String liveDate;
    @Size(min = 1, message = "내용을 입력해주세요.")
    private String content;
}