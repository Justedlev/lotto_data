package justedlev.lotto_data.controllers;

import justedlev.lotto_data.api.dto.SevenNumbersDTO;
import justedlev.lotto_data.api.dto.TicketDTO;
import justedlev.lotto_data.service.LottoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class LottoController {

    @Value("$justedlev.lotto_data.dataformot:yyyy-M-d")
    static public String DATA_FORMAT;

    @Autowired
    LottoService service;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/add/ticket")
    public ResponseEntity<?> addTicket(@RequestParam Integer number,
                                       @RequestParam Integer day,
                                       @RequestParam Integer month,
                                       @RequestParam Integer year,
                                       @RequestBody SevenNumbersDTO numbers) {
        TicketDTO.TicketDTOBuilder ticketBuilder = TicketDTO.builder()
                .numberOfTicket(number)
                .date(LocalDate.of(year, month, day))
                .combination(numbers);
        TicketDTO ticket = ticketBuilder.build();
        service.addTicket(ticket);
        log.debug("Added new ticket to db: {}", ticket);
        return ResponseEntity.ok(String.format("Added new ticket to db: %s", ticket));
    }

    @DeleteMapping("/delete/ticket")
    public ResponseEntity<?> deteleTicket(@RequestParam Integer number) {
        TicketDTO ticket = service.removeTicket(number);
        log.debug("Removed ticket from db: {}", ticket);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get/ticket")
    public ResponseEntity<?> getTicket(@RequestParam Integer number) {
        TicketDTO ticket = service.getTicket(number);
        log.debug("Received ticket from db: {}", ticket);
        if(ticket == null) {
            return noContent();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get/tickets")
    public ResponseEntity<?> getTickets(@RequestParam(required = false) String fromDate,
                                        @RequestParam(required = false) String toDate) {
        List<TicketDTO> ticketList;
        if(fromDate != null && toDate != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATA_FORMAT);
            ticketList = service.getTicketsOfDateRange(LocalDate.parse(fromDate, dtf), LocalDate.parse(toDate, dtf));
        } else {
            ticketList = service.getAllTickets();
        }
        log.debug("Received tickets from db: {}", ticketList);
        return ResponseEntity.ok(ticketList);
    }

    private ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

}
