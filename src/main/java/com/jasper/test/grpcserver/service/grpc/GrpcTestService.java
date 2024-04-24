package com.jasper.test.grpcserver.service.grpc;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import com.jasper.test.grpcserver.config.grpc.GRPCServerInterceptor;
import com.jasper.test.grpc.grpcserver.GRPCTestServiceGrpc;
import com.jasper.test.grpc.grpcserver.TestConnection.TestRequest;
import com.jasper.test.grpc.grpcserver.TestConnection.TestResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService(interceptors = GRPCServerInterceptor.class)
public class GrpcTestService extends GRPCTestServiceGrpc.GRPCTestServiceImplBase{

  @Override
  public void testConnectionTimeCost(TestRequest request, StreamObserver<TestResponse> responseObserver) {
    log.debug("[GrpcTestService] testConnectionTimeCost() request: {}", request);
    // Get the timestamp in second from the request
    Timestamp timestamp = request.getSendTimeStamp();
    long timestampInMilliseconds = getTimestampInMilliseconds(timestamp);
    // Get the current timestamp in second
    long currentTimeStamp = System.currentTimeMillis();
    // Calculate the time cost
    long timeCostInMilliseconds = currentTimeStamp - timestampInMilliseconds;
    // Create the response
    TestResponse response = TestResponse.newBuilder()
        .setSendTimeStamp(timestamp)
        .setReceiveTimeStamp(getTimestampInTimestamp(currentTimeStamp))
        .setProcessTimeCost(getTimestampInTimestamp(timeCostInMilliseconds))
        .build();
    // Send the response
    responseObserver.onNext(response);
    // Complete the response
    responseObserver.onCompleted();
  }

  private long getTimestampInMilliseconds(Timestamp timestamp) {
    return timestamp.getSeconds() * 1000 + timestamp.getNanos() / 1000000;
  }

  private Timestamp getTimestampInTimestamp(long timestampInMilliseconds) {
    long seconds = timestampInMilliseconds / 1000;
    int nanoSeconds = (int) ((timestampInMilliseconds % 1000) * 1000000);
    return Timestamp.newBuilder().setSeconds(seconds).setNanos(nanoSeconds).build();
  }
}
