package backend.reservation;

import backend.hotel.Hotel;
import backend.hotel.HotelRepository;
import backend.room.Room;
import backend.room.RoomRepository;
import backend.room.RoomService;
import backend.user.User;
import backend.user.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final HotelRepository hotelRepository = HotelRepository.getInstance();
    private final RoomRepository roomRepository = RoomRepository.getInstance();
    private final RoomService roomService = new RoomService();

    public Reservation findById( UUID id )
    {
        return reservationRepository .findById( id );
    }

    public void delete( UUID id )
    {
        reservationRepository .deleteById( id );
    }

    public List<Reservation> getReservationByHotelId( UUID hotelId )
    {
        return reservationRepository.getReservationsHotelById( hotelId );
    }

    public Reservation create( ReservationCreateDto reservationCreateDto )
    {
        boolean validated = validateReservationCreateDto( reservationCreateDto );

        if( validated )
        {
            BigDecimal priceOfReservation = roomService.calculateRoomPrice( reservationCreateDto.getRoomID(),
                    reservationCreateDto.getFromDate(), reservationCreateDto.getToDate() );
            Reservation reservation = new Reservation( reservationCreateDto, priceOfReservation );
            User user = userRepository.findById( reservationCreateDto.getUserID() );
            user.setBalance( user.getBalance().subtract( priceOfReservation ) );

            return reservationRepository.save( reservation );
        }

        return null;
    }

    private boolean validateReservationCreateDto( ReservationCreateDto reservationCreateDto )
    {
        boolean isValidData = validateUserHotelRoom( reservationCreateDto );
        if( isValidData )
        {
            if( validateReservationDates( reservationCreateDto ) )
            {
                if( validateBalance( reservationCreateDto ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateUserHotelRoom( ReservationCreateDto reservationCreateDto )
    {
        User user = userRepository.findById( reservationCreateDto.getUserID() );
        Hotel hotel = hotelRepository.findById( reservationCreateDto.getHotelID() );
        Room room = roomRepository.findById( reservationCreateDto.getRoomID() );
        if( user != null && hotel != null && room != null )
        {
            return true;
        }
        return false;
    }

    // 01.01.2023 - 10.01.2023
    // 05.01.2023 - 15.01.2023
    private boolean validateReservationDates( ReservationCreateDto reservationCreateDto )
    {
        LocalDate fromDate = reservationCreateDto.getFromDate();
        LocalDate toDate = reservationCreateDto.getToDate();

        if( fromDate.isAfter( toDate ) || fromDate.isBefore( LocalDate.now() ) )
        {
            return false;
        }

        List<Reservation> reservationsByRoomId = reservationRepository.getReservationsByRoomId( reservationCreateDto.getRoomID());

        for( Reservation reservation : reservationsByRoomId )
        {
            if( reservation.getFromDate().isAfter( fromDate ) && reservation.getToDate().isBefore( fromDate ) )
            {
                return false;
            }

            if( reservation.getFromDate().isAfter( toDate ) && reservation.getToDate().isBefore( toDate ) )
            {
                return false;
            }
        }

        return true;
    }

    private boolean validateBalance( ReservationCreateDto reservationCreateDto )
    {
        User user = userRepository.findById( reservationCreateDto.getUserID() );

        BigDecimal priceOfReservation = roomService.calculateRoomPrice( reservationCreateDto.getRoomID(),
                reservationCreateDto.getFromDate(), reservationCreateDto.getToDate() );
        if( user.getBalance().compareTo( priceOfReservation ) >= 0 )
        {
            return true;
        }
        return false;
    }
}
