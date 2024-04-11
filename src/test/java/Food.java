
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * 테스트 폴더에 별도로 존재하지 않으면 em.persist() 단계에서 예외 발생.
 * org.hibernate.UnknownEntityTypeException: Unable to locate persister: com.ssh.Food
 * 
 */
@Entity
@Table(name = "Food")
@Getter @Setter
public class Food {
    
    @Id @GeneratedValue
    private Long id;

    private String name;
    
}
