package com.justedlev.service.lottotickets.api.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NumbersDTO {

    Set<Integer> sixNumbers;
    Integer strong;

}
