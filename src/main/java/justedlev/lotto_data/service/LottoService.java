package justedlev.lotto_data.service;

import justedlev.lotto_data.api.dto.SevenNumbersDTO;
import justedlev.lotto_data.api.dto.TicketDTO;
import justedlev.lotto_data.domain.dao.GameRepository;
import justedlev.lotto_data.domain.entities.SevenNumbersEntity;
import justedlev.lotto_data.domain.entities.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LottoService implements ILotto {

    @Autowired
    GameRepository repo;

    @Override
    public TicketDTO addTicket(TicketDTO ticket) {
        if(!repo.existsById(ticket.getNumberOfTicket())) {
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
    public List<TicketDTO> getTicketsOfDateRange(LocalDate from, LocalDate to) {
        return repo.findByDateBetween(from, to).stream()
                .map(this::convertTicketEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO removeTicket(Integer numberOfTicket) {
        TicketDTO ticket = getTicket(numberOfTicket);
        if(ticket != null) {
            repo.deleteById(numberOfTicket);
            return ticket;
        }
        return null;
    }

    private TicketDTO convertTicketEntityToDTO(TicketEntity entity) {
        if(entity == null) {
            return null;
        }
        return TicketDTO.builder()
                .numberOfTicket(entity.getNumberOfTicket())
                .date(entity.getDate())
                .combination(convertSevenNumbersEntityToDTO(entity.getCombination()))
                .build();
    }

    private SevenNumbersDTO convertSevenNumbersEntityToDTO(SevenNumbersEntity combination) {
        return SevenNumbersDTO.builder()
                .first(combination.getFirst())
                .second(combination.getSecond())
                .third(combination.getThird())
                .fourth(combination.getFourth())
                .fifth(combination.getFifth())
                .sixth(combination.getSixth())
                .strong(combination.getStrong())
                .build();
    }

    private TicketEntity convertTicketDTOToEntity(TicketDTO ticket) {
        return TicketEntity.builder()
                .numberOfTicket(ticket.getNumberOfTicket())
                .date(ticket.getDate())
                .combination(convertSevenNumbersDTOToEntity(ticket.getCombination()))
                .build();
    }

    private SevenNumbersEntity convertSevenNumbersDTOToEntity(SevenNumbersDTO combination) {
        return SevenNumbersEntity.builder()
                .first(combination.getFirst())
                .second(combination.getSecond())
                .third(combination.getThird())
                .fourth(combination.getFourth())
                .fifth(combination.getFifth())
                .sixth(combination.getSixth())
                .strong(combination.getStrong())
                .build();
    }

}
