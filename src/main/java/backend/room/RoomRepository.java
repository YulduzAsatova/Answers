package backend.room;

import backend.common.BaseRepository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRepository extends BaseRepository<Room, UUID> {

    private static final String PATH = "src/main/java/backend/room/RoomDataSource.txt";

    private static RoomRepository roomRepository;

    private RoomRepository(){

    }

    @Override
    protected String getFilePath() {
        return PATH;
    }

    public static RoomRepository getInstance(){
        if(roomRepository == null){
            roomRepository = new RoomRepository();
        }
        return roomRepository;
    }
    public List<Room> findRoomsHotelById(UUID hotelId){
        List<Room>roomsHotelById = new ArrayList<>();
        for(Room room : readFromFile()){
            if(room.getHotelID().equals(hotelId)){
                roomsHotelById.add(room);
            }
        }
        return roomsHotelById;
    }
}
