package com.jasper.test.grpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GrpcServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GrpcServerApplication.class, args);
  }

}
