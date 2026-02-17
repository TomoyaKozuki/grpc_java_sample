# Javaで作るgRPCサーバー・クライアント実装サンプル
RPC（Remote Protocol Call）の一種であるRPCを用いた簡易的なサーバーとクライアントの実装を行いました。
## gRPC（Remote Protocol Call）
ネットワークを経由して、別サーバーにある関数を手元にあるかのように呼び出す仕組み。

gRPCの世界では、「JavaプログラムA」が「JavaプログラムB」の関数を呼び出すとき、Aがクライアントになります。その直後に、Bがさらに別の「サーバーC」の関数を呼ぶなら、Bはサーバーでありながらクライアントとしても振る舞うことになります。

### 使用技術
* 通信プロトコル：HTTP/2
* データ形式：Protocol Buffers（バイナリ形式）
  
## 通信プロトコル
通信プロトコルとは、コンピュータやネットワーク機器が互いに情報を正しくやり取りするために定められた約束事や手順のことをさします。人間同士が共通の言語を使わなければ意思疎通ができないのと同じように、異なる機器やソフトウェアも共通のルールに従って通信を行う必要があります。
## HTTP/2
HTTP/2とは、ウェブサーバー側が**複数のリクエスト**を同時に受け付けて処理することができる仕組み。
HTTP/1.1では、ウェブサーバーに対して原則１つずつしかリクエストを送信することができなかったため、HTTP/2の登場により従来の通信プロトコルよりも通信の効率を改善させることができた。
## Protocol Buffers
データ構造をシリアライズ（バイナリ形式に変換）及び、デシアライズを効率的に行うための方法。
Protocol Buffersによって定義したデータ構造（.protoファイル）を、protocコンパイラを通すことで各言語に対応したコードを自動的に生成する。（Javaの場合./gradlew generateProto）

##ディレクトリ構成
```
.
├── gradlew              # macOS/Linux用実行スクリプト
├── gradlew.bat          # Windows用実行スクリプト
├── gradle/wrapper/      # Gradle本体のダウンロード用設定
├── src/
│   ├── main/
│   │   ├── proto/       # .proto ファイル（通信の設計図）
│   │   └── java/        # サーバー・クライアントの実装コード
└── build.gradle         # ビルド設定（gRPCプラグイン等）
```
## サーバー・クライアントの実行手順
### コードの自動生成
.protoファイルからJavaの通信用スタブコードを自動生成します。
```
# Windows
.\gradlew.bat generateProto

# macOS / Linux
./gradlew generateProto
```
### サーバーの起動
サーバープログラムを起動し、クライアントからの接続待ち状態にします。
```
# Windows
.\gradlew.bat runServer

# macOS / Linux
./gradlew runServer
```
### クライアントの実行
新しいターミナルを開き、サーバーが起動していることを確認してからクライアントを実行してください。
```
# Windows
.\gradlew.bat runClient

# macOS / Linux
./gradlew runClient
```
正常に実行されると以下の出力が表示されます。
```サーバー側
> Task :runServer
Server started...
Received: Java-User
```
```クライアント側
> Task :runClient
Response from server: Hello, Java-User! This is Java Server.

[Incubating] Problems report is available at: file:///C:/Users/PC_User/Downloads/grpc_java_sample-main/grpc_java_sample-main/manager_java/build/reports/problems/problems-report.html

Deprecated Gradle features were used in this build, making it incompatible with Gradle 10.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/9.3.1/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 1s
6 actionable tasks: 1 executed, 5 up-to-date
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.3.1/userguide/configuration_cache_enabling.html
```
