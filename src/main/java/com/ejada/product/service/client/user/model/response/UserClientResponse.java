package com.ejada.product.service.client.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserClientResponse {

    private String id;

    private String name;

    private String job;

    private String createdAt;

}
