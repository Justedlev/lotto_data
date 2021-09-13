package justedlev.lotto_data.service;

import justedlev.lotto_data.api.dto.TicketDTO;

import java.time.LocalDate;
import java.util.List;

public interface ILotto {

    TicketDTO addTicket(TicketDTO ticket);
    TicketDTO getTicket(Integer numberOfTicket);
    List<TicketDTO> getAllTickets();
    List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to);
    TicketDTO removeTicket(Integer numberOfTicket);

}
