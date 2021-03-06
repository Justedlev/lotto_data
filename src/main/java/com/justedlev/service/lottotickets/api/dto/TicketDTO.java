package com.justedlev.service.lottotickets.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketDTO {

    Integer id;
    @JsonFormat
    LocalDate date;
    NumbersDTO combination;

}
