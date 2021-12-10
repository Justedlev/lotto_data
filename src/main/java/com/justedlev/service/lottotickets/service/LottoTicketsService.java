package com.justedlev.service.lottotickets.service;

import com.justedlev.service.lottotickets.api.dto.NumbersDTO;
import com.justedlev.service.lottotickets.api.dto.RepeatableNumberDTO;
import com.justedlev.service.lottotickets.api.dto.TicketDTO;
import com.justedlev.service.lottotickets.repository.GameRepository;
import com.justedlev.service.lottotickets.repository.entity.NumbersEntity;
import com.justedlev.service.lottotickets.repository.entity.TicketEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LottoTicketsService implements ILottoTickets {

    private final GameRepository repo;

    public LottoTicketsService(GameRepository repo) {
        this.repo = repo;
    }

    @Override
    public TicketDTO addTicket(TicketDTO ticket) {
        log.info("Received data : {}", ticket);
        if (!repo.existsById(ticket.getId())) {
            repo.save(convertTicketDTOToEntity(ticket));
            return ticket;
        }
        return null;
    }

    @Override
    public TicketDTO getTicket(Integer numberOfTicket) {
        return convertTicketEntityToDTO(repo.findById(numberOfTicket).orElse(null));
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return repo.findAll().stream()
                .map(this::convertTicketEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepeatableNumberDTO> getRepeatableAllNumbersOfDateRange(LocalDate from, LocalDate to) {
        List<Integer> numbers = new ArrayList<>();
        getTicketsOfDateRange(from, to).stream()
                .map(t -> t.getCombination().getSixNumbers())
                .collect(Collectors.toList())
                .forEach(numbers::addAll);
        return getRepeatables(numbers);
    }

    @Override
    public List<RepeatableNumberDTO> getRepeatableStrongNumbersOfDateRange(LocalDate from, LocalDate to) {
        List<Integer> numbers = getTicketsOfDateRange(from, to).stream()
                .map(t -> t.getCombination().getStrong())
                .collect(Collectors.toList());
        return getRepeatables(numbers);
    }

    @Override
    public List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to) {
        return repo.findByDateBetween(from, to.plusDays(1)).stream()
                .map(this::convertTicketEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO removeTicket(Integer numberOfTicket) {
        TicketDTO ticket = getTicket(numberOfTicket);
        if (ticket != null) {
            repo.deleteById(numberOfTicket);
            return ticket;
        }
        return null;
    }

    private List<RepeatableNumberDTO> getRepeatables(List<Integer> list) {
        List<RepeatableNumberDTO> repeatables = new ArrayList<>();
        list.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .forEach((n, c) -> repeatables.add(RepeatableNumberDTO.builder().number(n).count(c).build()));
        return repeatables;
    }

    private TicketDTO convertTicketEntityToDTO(TicketEntity entity) {
        if (entity == null) {
            return null;
        }
        return TicketDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .combination(convertNumbersEntityToDTO(entity.getCombination()))
                .build();
    }

    private NumbersDTO convertNumbersEntityToDTO(NumbersEntity combination) {
        return NumbersDTO.builder()
                .sixNumbers(combination.getSixNumbers())
                .strong(combination.getStrong())
                .build();
    }

    private TicketEntity convertTicketDTOToEntity(TicketDTO ticket) {
        return TicketEntity.builder()
                .id(ticket.getId())
                .date(ticket.getDate())
                .combination(convertNumbersDTOToEntity(ticket.getCombination()))
                .build();
    }

    private NumbersEntity convertNumbersDTOToEntity(NumbersDTO combination) {
        return NumbersEntity.builder()
                .sixNumbers(combination.getSixNumbers())
                .strong(combination.getStrong())
                .build();
    }

}
