package backend.room;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity<UUID> implements Serializable {
    private Integer floor;
    private RoomType roomType;
    private UUID hotelID;
    private BigDecimal price;


}
