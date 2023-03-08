package cupraccoon.myboard.controller.member;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank
    private String loginId;
    @NotEmpty
    private String nickname;
    @NotBlank
    private String password;


}
