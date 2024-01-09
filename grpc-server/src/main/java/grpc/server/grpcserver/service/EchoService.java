package grpc.server.grpcserver.service;

import com.google.protobuf.Empty;
import grpc.server.EchoServiceGrpc;
import grpc.server.Message;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {
    @Override
    public void getHello(Empty request, StreamObserver<Message> responseObserver) {
        var response = Message.newBuilder().setParam("hello bro").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sayHello(Message request, StreamObserver<Message> responseObserver) {
        var response = Message.newBuilder().setParam(request.getParam()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
