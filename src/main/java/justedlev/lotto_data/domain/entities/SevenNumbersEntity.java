package justedlev.lotto_data.domain.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class SevenNumbersEntity {

    Integer first;
    Integer second;
    Integer third;
    Integer fourth;
    Integer fifth;
    Integer sixth;
    Integer strong;

}
