package justedlev.lotto_data.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "tickets")
public class TicketEntity {

    @Id
    Integer numberOfTicket;
    @JsonFormat(pattern="d.M.yy")
    LocalDate date;
    SevenNumbersEntity combination;

}
