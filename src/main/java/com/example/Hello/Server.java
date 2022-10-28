package com.example.Hello;

import demo.proto.HelloWorldGrpc;
import demo.proto.HelloWorldProto;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(1234).addService(new ServerImpl()).build();
        server.start();
        System.out.println(" The server is running on port 1234");

        server.awaitTermination();
    }

    private static class ServerImpl extends HelloWorldGrpc.HelloWorldImplBase{
        @Override
        public void greet(HelloWorldProto.GreetRequest request, StreamObserver<HelloWorldProto.GreetResponse> responseObserver){
            String name = request.getName();
            System.out.println(name);

            HelloWorldProto.GreetResponse response = HelloWorldProto.GreetResponse.newBuilder()
                    .setResponse("Hello " + name)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
