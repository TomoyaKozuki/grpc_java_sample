package com.example.grpc.sample;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        // 50051番ポートでサーバーを起動
        Server server = ServerBuilder.forPort(50051)
            .addService(new GreetServiceImpl())//特定のサービスのリクエスト来た時に、どのインスタンス（GreetServiceImpl）に処理を飛ばすかをマッピング
            .build();

        System.out.println("Server started...");
        server.start();// サーバーが起動した後は、リクエストを待ち続ける
        server.awaitTermination();
    }

    // 通信の実体（ロジック）.proto ファイルから自動生成された「土台」を継承
    static class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            String name = request.getName();
            System.out.println("Received: " + name);

            HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Hello, " + name + "! This is Java Server.")
                .build();

            responseObserver.onNext(response); // データを送る
            responseObserver.onCompleted();    // 通信完了
        }
    }
}