package cupraccoon.myboard.controller.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
