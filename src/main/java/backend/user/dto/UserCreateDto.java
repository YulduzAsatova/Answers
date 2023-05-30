package backend.user.dto;

import backend.user.UserType;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import  lombok.NoArgsConstructor;
import  lombok.Data;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserCreateDto {
    private UserType userType;
    private String name;
    private BigDecimal balance;
    private String password;
}
