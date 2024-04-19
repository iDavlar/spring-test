package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.UserEntity;
import by.davlar.spring.service.dto.UserDto;

public class UserMapper {
    public static UserEntity UserDtoToUser(UserDto dto) {
        return new UserEntity(dto.getId(), dto.getName());
    }

    public static UserDto UserToUserDto(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getName());
    }
}
