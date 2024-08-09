package com.codecake.spotify_clone_be.usercontext.mapper;

import com.codecake.spotify_clone_be.usercontext.ReadUserDTO;
import com.codecake.spotify_clone_be.usercontext.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ReadUserDTO readUserDTOToUser(User entity);
}

