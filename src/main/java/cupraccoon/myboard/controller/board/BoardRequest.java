package cupraccoon.myboard.controller.board;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String userName;
    private String password;
    private String title;
    private String content;
    private boolean isSignedUser;
}
