package mall.query;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

@Entity
@Table(name = "Menu_table")
@Data
@Relation(collectionRelation = "menus")
public class Menu {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
