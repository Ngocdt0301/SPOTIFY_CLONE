package com.codecake.spotify_clone_be.userContext.mapper;

import com.codecake.spotify_clone_be.userContext.ReadUserDTO;
import com.codecake.spotify_clone_be.userContext.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO readUserDTOToUser(User entity);
}

