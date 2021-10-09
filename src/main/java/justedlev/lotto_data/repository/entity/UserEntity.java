package justedlev.lotto_data.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class UserEntity {

    @Id
    private String username;
    private String hashCode;
    private Set<String> roles;

}
