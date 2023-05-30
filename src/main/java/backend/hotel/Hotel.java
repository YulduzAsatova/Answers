package backend.hotel;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends BaseEntity<UUID>implements Serializable {
    private String name;
    private String location;
}
