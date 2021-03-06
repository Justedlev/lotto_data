package com.justedlev.service.lottotickets.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "tickets")
public class TicketEntity {

    @Id
    Integer id;
    @JsonFormat
    LocalDate date;
    NumbersEntity combination;

}
