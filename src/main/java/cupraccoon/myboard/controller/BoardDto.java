package cupraccoon.myboard.controller;


import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String nickName;
    private String password;
    private String title;
    private String content;
    private boolean isSignedUser;
}
