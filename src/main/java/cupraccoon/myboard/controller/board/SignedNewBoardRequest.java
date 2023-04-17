package cupraccoon.myboard.controller.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignedNewBoardRequest {
    private String title;
    private String content;
}
