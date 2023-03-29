import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class GrpcServer {

    private static final int PORT = 50051;

    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(PORT).addService(new ItemService()).build();

        try {
            server.start();
            System.out.println("Server started");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
