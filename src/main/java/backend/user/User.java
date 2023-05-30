package backend.user;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class User extends BaseEntity<UUID> implements Serializable {
    private UserType userType;
    private String name;
    private BigDecimal balance;
    private byte[] password;
    private UUID salt;

}
