import backend.hotel.Hotel;
import backend.hotel.HotelService;
import backend.user.User;
import backend.user.UserService;
import backend.user.UserType;
import backend.user.dto.UserCreateDto;
import backend.user.dto.UserResponseDto;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        HotelService hotelService = new HotelService();
        //hotelService.create(new Hotel("Komolon","Toshkent"));
        userService.signUp(new UserCreateDto(UserType.ADMIN,"Botir", BigDecimal.valueOf(1000),"2222"));
        List<User> list = userService.getList();
        System.out.println(list);
    }
}
