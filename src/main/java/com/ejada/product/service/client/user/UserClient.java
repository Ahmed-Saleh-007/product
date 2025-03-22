package com.ejada.product.service.client.user;

import com.ejada.product.service.client.user.model.reqeust.LoginClientRequest;
import com.ejada.product.service.client.user.model.reqeust.UserClientRequest;
import com.ejada.product.service.client.user.model.response.TokenClientResponse;
import com.ejada.product.service.client.user.model.response.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user", url = "${client.user.url}", configuration = UserClientConfig.class)
public interface UserClient {

    @PostMapping(value = "${client.user.login-path}")
    TokenClientResponse login(@RequestBody LoginClientRequest request);

    @PostMapping(value = "${client.user.create-user-path}")
    UserClientResponse createUser(@RequestBody UserClientRequest userClientRequest);

}
