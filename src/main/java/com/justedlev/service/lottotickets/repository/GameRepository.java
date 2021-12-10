package com.justedlev.service.lottotickets.repository;

import com.justedlev.service.lottotickets.repository.entity.TicketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends MongoRepository<TicketEntity, Integer> {

    List<TicketEntity> findByDateBetween(LocalDate from, LocalDate to);

}
