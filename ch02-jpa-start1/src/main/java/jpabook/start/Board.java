package jpabook.start;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1, allocationSize = 50
)

//@TableGenerator(
//        name = "BOARD_SEQ_GENEERATOR",
//        table =  "MY_SEQUENCE",
//        pkColumnValue = "BOARD_SEQ", allocationSize = 50
//)

public class Board {
    //@GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR")
    private Long id;

    private String userName;

    public Long getId(){
        return id;

    }
}
