package demo;
import demo.proto.HelloWorldGrpc;
import demo.proto.HelloWorldProto;
import io.grpc.ServerBuilder;
import io.grpc.internal.ServerImpl;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(12345).addService(new ServerImpl()).build();
        server.start();
        System.out.println(" The server is running on port 12345");

        server.awaitTermination();
    }

    private static class ServerImpl extends HelloWorldGrpc.HelloWorldImplBase{
        @Override
        public void greet(HelloWorldProto.GreetRequest request, StreamObserver<HelloWorldProto.GreetResponse> responseObserver){
            String name = request.getName();

            HelloWorldProto.GreetResponse response = HelloWorldProto.GreetResponse.newBuilder().setResponse("Hello " + name).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
