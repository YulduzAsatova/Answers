package backend.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class ReservationCreateDto {
    private UUID userID;
    private UUID roomID;
    private UUID hotelID;
    private LocalDate fromDate;
    private LocalDate toDate;

}
