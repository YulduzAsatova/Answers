package backend.hotel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class HotelService {
    private final HotelRepository hotelRepository = HotelRepository.getInstance();

    public Hotel create(Hotel hotel){
        hotel.setId(UUID.randomUUID());
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());
        return hotelRepository.save(hotel);
    }
    public void delete(UUID uuid){
        hotelRepository.deleteById(uuid);
    }
    public List<Hotel> getAll(){
        return hotelRepository.getAll();
    }
    public Hotel findById (UUID uuid){
        return hotelRepository.findById(uuid);
    }

}
