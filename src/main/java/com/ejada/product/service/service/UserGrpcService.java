package com.ejada.product.service.service;

import com.ejada.product.grpc.UserGrpcRequest;
import com.ejada.product.grpc.UserGrpcResponse;
import com.ejada.product.grpc.UserServiceGrpc;
import com.ejada.product.service.model.mapper.UserMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserGrpcRequest grpcRequest,
                           StreamObserver<UserGrpcResponse> responseObserver) {
        responseObserver.onNext(userMapper.mapToUserGrpcResponse(
                userService.createUser(userMapper.mapToUserRequest(grpcRequest))));
        responseObserver.onCompleted();
    }

}
