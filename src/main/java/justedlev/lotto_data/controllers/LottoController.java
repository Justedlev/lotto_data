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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> addTicket(@RequestBody TicketDTO ticket) {
        TicketDTO ticketDto = service.addTicket(ticket);
        log.debug("Added new ticket to db: {}", ticketDto);
        return ResponseEntity.ok(ticketDto);
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
        if (ticket == null) {
            return noContent();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get/tickets")
    public ResponseEntity<?> getTickets(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-M-d") LocalDate fromDate,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-M-d") LocalDate toDate) {
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
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") LocalDate toDate) {
        List<RepeatableNumberDTO> ticketRepeatableNumberOfDateRange = service.getRepeatableNumbersOfDateRange(fromDate, toDate);
        log.debug("Received tickets from db: {}", ticketRepeatableNumberOfDateRange);
        return ResponseEntity.ok(ticketRepeatableNumberOfDateRange);
    }

    @GetMapping("/get/repeatableNumbers")
    public ResponseEntity<?> getRepeatableNumberOfDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-M-d") LocalDate fromDate,
                                                            @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") LocalDate toDate,
                                                            @RequestParam String numberCombination) {
        List<RepeatableNumberDTO> ticketRepeatableNumberOfDateRange;
        CombinationNames cn = CombinationNames.valueOf(numberCombination.toUpperCase());
        switch (cn) {
            case FIRST:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeFirst(fromDate, toDate);
                break;
            case SECOND:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeSecond(fromDate, toDate);
                break;
            case THIRD:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeThird(fromDate, toDate);
                break;
            case FOURTH:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeFourth(fromDate, toDate);
                break;
            case FIFTH:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeFifth(fromDate, toDate);
                break;
            case SIXTH:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeSixth(fromDate, toDate);
                break;
            case STRONG:
                ticketRepeatableNumberOfDateRange = service.getRepeatableNumberOfDateRangeStrong(fromDate, toDate);
                break;
            default:
                ticketRepeatableNumberOfDateRange = null;
        }
        log.debug("Received tickets from db: {}", ticketRepeatableNumberOfDateRange);
        return ResponseEntity.ok(ticketRepeatableNumberOfDateRange);
    }

    private ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

}
