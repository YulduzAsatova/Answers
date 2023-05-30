package backend.hotel;

import backend.common.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelRepository extends BaseRepository<Hotel, UUID> {

    private static  final String PATH = "src/main/java/backend/hotel/HotelDataSource.txt";

    private static HotelRepository hotelRepository;

    private HotelRepository(){

    }


    @Override
    protected String getFilePath() {
        return PATH;
    }

    public static HotelRepository getInstance(){
        if(hotelRepository == null){
            hotelRepository = new HotelRepository();
        }
        return hotelRepository;
    }
}
