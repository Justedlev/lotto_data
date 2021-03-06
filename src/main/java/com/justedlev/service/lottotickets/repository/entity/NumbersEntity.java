package com.justedlev.service.lottotickets.repository.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class NumbersEntity {

    Set<Integer> sixNumbers;
    Integer strong;

}
