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
    private static final int NUM_OF_CHUNKS = 5;

    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(PORT).addService(new GRPCServiceImpl()).build();

        try {
            server.start();
            System.out.println("Server started");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class GRPCServiceImpl extends ServiceNameGrpc.ServiceNameImplBase {

        public void unaryProcedure(TheRequest req,
                                   StreamObserver<TheResponse> responseObserver) {
            String msg;
            System.out.println("...called UnaryProcedure - start");
            if (req.getAge() > 18)
                msg = "Mr/Ms "+ req.getName();
            else
                msg = "Boy/Girl";
            TheResponse response = TheResponse.newBuilder()
                    .setMessage("Hello " + msg)
                    .build();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            System.out.println("...called UnaryProcedure - end");
        }

        public void streamProcedure(TheRequest req,
                                   StreamObserver<TheResponse> responseObserver) {
            System.out.println("...called streamProcedure - start");
            for( int i=0; i<NUM_OF_CHUNKS; i++) {
                TheResponse response = TheResponse.newBuilder()
                        .setMessage("Stream chunk " + (i+1)).build();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
            System.out.println("...called UnaryProcedure - end");
        }

        public void fibProcedure(FibRequest req,
                                    StreamObserver<FibResponse> responseObserver) {
            System.out.println("...called fibProcedure - start");
            int current = 0;
            int last = 0;
            int secondLast = 0;
            for( int i=0; i< req.getIle(); i++) {
                if(last == 0 && secondLast == 0) {
                    current = 1;
                    secondLast = 1;
                }
                else if (secondLast == 1 && last == 0) {
                    current = 1;
                    last = 1;
                }
                else {
                    current = secondLast + last;
                    secondLast = last;
                    last = current;
                }
                FibResponse response;
                if(req.getSzescian()) {
                    response = FibResponse.newBuilder()
                            .setLiczba(current)
                            .setLiczb2(current * current * current)
                            .build();
                }
                else {
                    response = FibResponse.newBuilder()
                            .setLiczba(current)
                            .setLiczb2(current * current)
                            .build();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
            System.out.println("...called fibProcedure - end");
        }

        public void fileSendProcedure(FileSendRequest req, StreamObserver<FileSendResponse> ressponseObserver) {
            System.out.println("...sending file start");
            final String fileName = req.getFileName();
            final int CHUNK_SIZE = 250_000;
            try(InputStream is = new FileInputStream("/Users/kacperwojcicki/University/RSI/Lab3/GrpcLab3/GrpcServer/src/main/resources/"
                    + fileName)) {
                System.out.println("In try block");
                System.out.println(is.available());
                while(is.available() > 0) {
                    System.out.println("Sending chunk");
                     byte[] bytes = is.readNBytes(CHUNK_SIZE);
                     FileSendResponse resp = FileSendResponse.newBuilder()
                             .setChunk(ByteString.copyFrom(bytes))
                             .setFileName(fileName)
                             .build();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ressponseObserver.onNext(resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ressponseObserver.onCompleted();
            System.out.println("...sending file end");
        }


    }
}
