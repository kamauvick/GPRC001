package com.example.Hello;

import demo.proto.HelloWorldGrpc;
import demo.proto.HelloWorldProto;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
@RestController
public class Client {
    public static String myName;
    public static void main(String[] args) {
        Channel channel = ManagedChannelBuilder
                .forAddress("localhost", 1234)
                .usePlaintext()
                .build();
        HelloWorldGrpc.HelloWorldBlockingStub helloWorldService = HelloWorldGrpc.newBlockingStub(channel);
        HelloWorldProto.GreetResponse response = helloWorldService.greet(HelloWorldProto
                .GreetRequest
                .newBuilder()
                .setName("Vick")
                .build());
        System.out.println("Server says: " + response.getResponse());
    }
}

