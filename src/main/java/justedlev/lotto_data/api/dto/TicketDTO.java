package justedlev.lotto_data.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketDTO {

    Integer numberOfTicket;
    @JsonFormat(pattern="d.MM.yyyy")
    LocalDate date;
    SevenNumbersDTO combination;

}
