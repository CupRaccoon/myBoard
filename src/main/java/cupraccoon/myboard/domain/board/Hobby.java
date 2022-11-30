package cupraccoon.myboard.domain.board;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("H")
@Getter @Setter
public class Hobby extends Board {

}
