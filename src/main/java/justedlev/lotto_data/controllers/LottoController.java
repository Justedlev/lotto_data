package justedlev.lotto_data.controllers;

import justedlev.lotto_data.api.dto.CombinationNames;
import justedlev.lotto_data.api.dto.RepeatableNumberDTO;
import justedlev.lotto_data.api.dto.TicketDTO;
import justedlev.lotto_data.service.LottoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
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
    public ResponseEntity<?> addTicket(@RequestBody TicketDTO ticket) {
        log.debug("Received data: {}", ticket);
        TicketDTO ticketDto = service.addTicket(ticket);
        log.debug("Added new ticket to db: {}", ticketDto);
        return ResponseEntity.ok(ticketDto);
    }

    @DeleteMapping("/delete/ticket")
    public ResponseEntity<?> deteleTicket(@RequestParam Integer number) {
        log.debug("Received data: {}", number);
        TicketDTO ticket = service.removeTicket(number);
        log.debug("Removed ticket from db: {}", ticket);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get/ticket")
    public ResponseEntity<?> getTicket(@RequestParam Integer number) {
        log.debug("Received data: {}", number);
        TicketDTO ticket = service.getTicket(number);
        log.debug("Received ticket from db: {}", ticket);
        if (ticket == null) {
            return noContent();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get/tickets")
    public ResponseEntity<?> getTickets(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-M-d") LocalDate fromDate,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-M-d") LocalDate toDate) {
        log.debug("Received data: {}, {}", fromDate, toDate);
        List<TicketDTO> ticketList;
        if (fromDate != null && toDate != null) {
            ticketList = service.getTicketsOfDateRange(fromDate, toDate);
        } else {
            ticketList = service.getAllTickets();
        }
        log.debug("Received tickets from db: {}", ticketList);
        return ResponseEntity.ok(ticketList);
    }

    @GetMapping("/get/repeatableNumbers")
    public ResponseEntity<?> getRepeatableNumberOfDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-M-d") LocalDate fromDate,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") LocalDate toDate,
                                                            @RequestParam String s) {
        log.debug("Received data: {}, {}, {}", fromDate, toDate, s);
        List<RepeatableNumberDTO> list;
        if(CombinationNames.valueOf(s.toUpperCase()).equals(CombinationNames.ALL)) {
            list = service.getRepeatableAllNumbersOfDateRange(fromDate, toDate);
        } else if (CombinationNames.valueOf(s.toUpperCase()).equals(CombinationNames.STRONG)) {
            list = service.getRepeatableStrongNumbersOfDateRange(fromDate, toDate);
        } else {
            list = null;
        }
        log.debug("Received tickets from db: {}", list);
        return ResponseEntity.ok(list);
    }

    private ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

}
