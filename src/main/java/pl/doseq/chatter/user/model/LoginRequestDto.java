package pl.doseq.chatter.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;

}
