package backend.reservation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity<UUID> implements Serializable {
    private UUID userID;
    private UUID roomID;
    private UUID hotelID;
    private BigDecimal price;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Reservation(ReservationCreateDto createDto,BigDecimal price){
        this.id = UUID.randomUUID();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

        this.userID = createDto.getUserID();
        this.roomID = createDto.getRoomID();
        this.hotelID = createDto.getHotelID();

        this.price = price;
        this.fromDate = createDto.getFromDate();
        this.toDate = createDto.getToDate();

    }

}
