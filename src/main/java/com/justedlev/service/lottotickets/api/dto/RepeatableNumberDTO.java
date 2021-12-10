package com.justedlev.service.lottotickets.api.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RepeatableNumberDTO {

    private Integer number;
    private Long count;

}
