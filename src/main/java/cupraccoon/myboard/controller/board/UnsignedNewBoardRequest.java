package cupraccoon.myboard.controller.board;


import cupraccoon.myboard.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnsignedNewBoardRequest {
    private String userName;
    private String password;
    private String title;
    private String content;


}
