package com.jasper.test.grpcserver.config.grpc;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GRPCServerInterceptor implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> serverCall,
      Metadata metadata,
      ServerCallHandler<ReqT, RespT> serverCallHandler) {
    log.info(
        "[GRPC][GrpcService][Method][{}]", serverCall.getMethodDescriptor().getServiceName());
    log.info("[GRPC][GrpcService][Metadata][{}]", metadata.toString());
    return serverCallHandler.startCall(serverCall, metadata);
  }
}
