package backend.room;

import backend.hotel.Hotel;
import backend.hotel.HotelRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RoomService {

    private final RoomRepository roomRepository = RoomRepository.getInstance();
    private final HotelRepository hotelRepository = HotelRepository.getInstance();

    public Room create (Room room){
        Hotel hotel = hotelRepository.findById(room.getHotelID());
        if(hotel != null){
            room.setCreated(LocalDateTime.now());
            room.setUpdated(LocalDateTime.now());
            room.setId(UUID.randomUUID());
            return roomRepository.save(room);
        }
        return null;
    }
    public Room findById(UUID id){
        return roomRepository.findById(id);
    }

    public void delete(UUID id){
        roomRepository.deleteById(id);
    }

    public List<Room> findRoomsByHotelId(UUID hotelId){
        return roomRepository.findRoomsHotelById(hotelId);
    }

    public BigDecimal calculateRoomPrice(UUID roomId, LocalDate fromDate, LocalDate toDate)
    {
       Room room = roomRepository.findById( roomId );

        LocalDate tempFromDate = fromDate;
        long days = 0;

        while( !tempFromDate.equals( toDate ) )
        {
            days++;
            tempFromDate = tempFromDate.plusDays( 1 );
        }

        return room.getPrice().multiply( BigDecimal.valueOf( days ) );
    }
}