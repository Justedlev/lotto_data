package justedlev.lotto_data.service;

import justedlev.lotto_data.api.dto.RepeatableNumberDTO;
import justedlev.lotto_data.api.dto.TicketDTO;

import java.time.LocalDate;
import java.util.List;

public interface ILotto {

    TicketDTO addTicket(TicketDTO ticket);
    TicketDTO getTicket(Integer numberOfTicket);
    List<TicketDTO> getAllTickets();
    List<RepeatableNumberDTO> getRepeatableAllNumbersOfDateRange(LocalDate from, LocalDate to);
    List<RepeatableNumberDTO> getRepeatableStrongNumbersOfDateRange(LocalDate from, LocalDate to);
    List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to);
    TicketDTO removeTicket(Integer numberOfTicket);

}
