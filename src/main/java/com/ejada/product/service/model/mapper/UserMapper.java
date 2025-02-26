package com.ejada.product.service.model.mapper;

import com.ejada.product.service.client.user.model.reqeust.LoginClientRequest;
import com.ejada.product.service.client.user.model.reqeust.UserClientRequest;
import com.ejada.product.service.client.user.model.response.TokenClientResponse;
import com.ejada.product.service.client.user.model.response.UserClientResponse;
import com.ejada.product.service.model.request.LoginRequest;
import com.ejada.product.service.model.request.UserRequest;
import com.ejada.product.service.model.response.TokenResponse;
import com.ejada.product.service.model.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    LoginClientRequest mapToLoginClientRequest(LoginRequest request);

    TokenResponse mapToTokenResponse(TokenClientResponse response);

    UserClientRequest mapToUserClientRequest(UserRequest request);

    UserResponse mapToUserResponse(UserClientResponse response);

}
