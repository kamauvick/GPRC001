package demo;

import demo.proto.HelloWorldGrpc;
import demo.proto.HelloWorldProto;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", 12345).usePlaintext().build();
        HelloWorldGrpc.HelloWorldBlockingStub helloWorldService = HelloWorldGrpc.newBlockingStub(channel);

        System.out.println("What is your name: ");
        String name = new Scanner(System.in).nextLine();
        HelloWorldProto.GreetResponse response = helloWorldService.greet(HelloWorldProto.GreetRequest.newBuilder().setName(name).build());

        System.out.println("Server says: " + response.getResponse());
    }
}
