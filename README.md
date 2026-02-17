# Javaで作るgRPCサーバー・クライアント実装サンプル
RPC（Remote Protocol Call）の一種であるRPCを用いた簡易的なサーバーとクライアントの実装を行いました。
## gRPC（Remote Protocol Call）
ネットワークを経由して、別サーバーにある関数を手元にあるかのように呼び出す仕組み。
以下の技術を使用している。
* 通信プロトコル：HTTP/2
* データ形式：Protocol Buffers（バイナリ形式）
### gRPCの特徴
