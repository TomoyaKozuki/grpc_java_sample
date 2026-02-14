package com.example.grpc.sample;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SimpleClient {
    public static void main(String[] args) {
        // サーバーへの接続路（Channel）を作る
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext() // usePlaintext(): 本来 gRPC は暗号化（TLS）が標準ですが、テスト用に「暗号化なしで繋ぐ」という設定をしています。
            .build();

        // 通信用スタブ（代理人）を作る
        GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);

        // リクエスト送信
        HelloResponse response = stub.sayHello(HelloRequest.newBuilder().setName("Java-User").build());

        System.out.println("Response from server: " + response.getMessage());

        channel.shutdown();
    }
}