package backend.reservation;

import backend.common.BaseRepository;
import backend.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationRepository extends BaseRepository<Reservation, UUID> {

    private static final String PATH = "src/main/java/backend/reservation/ReeservationDataSource.txt";

    private static ReservationRepository reservationRepository;

    private ReservationRepository(){

    }

    @Override
    protected String getFilePath() {
        return PATH;
    }

    public static ReservationRepository getInstance(){
        if(reservationRepository == null){
            reservationRepository = new ReservationRepository();
        }
        return reservationRepository;
    }
    public List<Reservation> getReservationsHotelById(UUID hotelId){
        List<Reservation> reservationList = new ArrayList<>();
        for(Reservation reservation : readFromFile()){
            if(reservation.getHotelID().equals(hotelId)){
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }

    public List<Reservation> getReservationsByRoomId( UUID roomId )
    {
        List<Reservation> reservationList = new ArrayList<>();
        for( Reservation reservation : readFromFile() )
        {
            if( reservation.getRoomID().equals( roomId ) )
            {
                reservationList.add( reservation );
            }
        }
        return reservationList;
    }

}
