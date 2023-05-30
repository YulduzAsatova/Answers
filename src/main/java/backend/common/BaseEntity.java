package backend.common;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity <ID>{
    protected UUID id;
    protected LocalDateTime created;
    protected LocalDateTime updated;

}
