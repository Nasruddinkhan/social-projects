package com.mypractice.microservice.usermgtsvc.service.control;

import com.mypractice.microservice.socialcore.dto.UserDto;

public interface UserControl {
    void saveUser(final UserDto userDto);
}
