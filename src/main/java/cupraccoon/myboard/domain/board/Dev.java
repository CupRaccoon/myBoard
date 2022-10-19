package cupraccoon.myboard.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("D")
public class Dev extends Board {
    public Dev(){

    }

}
