package com.justedlev.service.lottotickets.service;

import com.justedlev.service.lottotickets.api.dto.RepeatableNumberDTO;
import com.justedlev.service.lottotickets.api.dto.TicketDTO;

import java.time.LocalDate;
import java.util.List;

public interface ILottoTickets {

    TicketDTO addTicket(TicketDTO ticket);

    TicketDTO getTicket(Integer numberOfTicket);

    List<TicketDTO> getAllTickets();

    List<RepeatableNumberDTO> getRepeatableAllNumbersOfDateRange(LocalDate from, LocalDate to);

    List<RepeatableNumberDTO> getRepeatableStrongNumbersOfDateRange(LocalDate from, LocalDate to);

    List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to);

    TicketDTO removeTicket(Integer numberOfTicket);

}
