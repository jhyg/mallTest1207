package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderDetailsView_table")
@Data
public class OrderDetailsView {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
