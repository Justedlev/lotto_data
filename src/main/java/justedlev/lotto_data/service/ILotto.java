package justedlev.lotto_data.service;

import justedlev.lotto_data.api.dto.TicketDTO;

import java.time.LocalDate;
import java.util.List;

public interface ILotto {

    void addTicket(TicketDTO ticket);
    Integer getLastTicketNumber();
    TicketDTO getTicket(Integer number);
    List<TicketDTO> getAllTickets();
    List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to);
    TicketDTO removeTicket(Integer number);

}
