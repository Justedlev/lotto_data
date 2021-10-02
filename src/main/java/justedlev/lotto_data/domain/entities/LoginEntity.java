package justedlev.lotto_data.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "admin")
public class LoginEntity {

    @Id
    private String _id;
    private String nickname;
    private String password;

}
