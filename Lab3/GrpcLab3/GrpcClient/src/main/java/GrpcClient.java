import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Scanner;

public class GrpcClient {

    private static ServiceNameGrpc.ServiceNameStub nonbStub;
    public static void main(String[] args) throws InterruptedException {
        String address = "localhost"; //here we use service on the same host
        int port = 50051;
        ServiceNameGrpc.ServiceNameBlockingStub bStub;
        System.out.println("Running gRPC client...");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port) .usePlaintext().build();

        nonbStub = ServiceNameGrpc.newStub(channel);
        bStub = ServiceNameGrpc.newBlockingStub(channel);


        FileSendRequest fileSendRequest = FileSendRequest.newBuilder()
                        .setFileName("img1.jpeg")
                        .build();
        nonbStub.fileSendProcedure(fileSendRequest, new FileSendObs());
        promptEnterKey();

        channel.shutdown();
    }

    private static void callUnaryBlocking() {

    }

    private static void callUnaryNonBlocking() {

    }

    private static void callStreamBlocking() {

    }

    private static void callStreamNonBlocking() {
        
    }

    private static class UnaryObs implements StreamObserver<TheResponse> {
        public void onNext(TheResponse theResponse) {
            System.out.println("-->async unary onNext: " +
                    theResponse.getMessage());
        }
        public void onError(Throwable throwable) {
            System.out.println("-->async unary onError");
        }
        public void onCompleted() {
            System.out.println("-->async unary onCompleted");
        }
    }

    private static class FibObs implements StreamObserver<FibResponse> {
        public void onNext(FibResponse theResponse) {
            System.out.println("-->async fib onNext: " +
                    theResponse.getLiczba() + " and " + theResponse.getLiczb2());
        }
        public void onError(Throwable throwable) {
            System.out.println("-->async fib onError");
        }
        public void onCompleted() {
            System.out.println("-->async fib onCompleted");
        }
    }

    private static class FileSendObs implements StreamObserver<FileSendResponse> {
        private static OutputStream os = null;

        public void onNext(FileSendResponse theResponse) {
            if(os == null) {
                try {
                    os = new FileOutputStream(theResponse.getFileName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                os.write(theResponse.getChunk().toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }
        public void onCompleted() {
            try {
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("fileSend onCompleted!");
        }
    }


    private static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
