package justedlev.lotto_data.api.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginDTO {

    private String nickname;
    private String password;

}
