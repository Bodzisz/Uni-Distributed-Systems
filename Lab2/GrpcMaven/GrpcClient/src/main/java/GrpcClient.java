import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

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

        FibRequest fibRequest = FibRequest.newBuilder()
                .setIle(5)
                .setSzescian(true)
                .build();

//        TheRequest request = TheRequest.newBuilder().setName("Mariusz")
//                .setAge(33).build();

//        System.out.println("...calling unaryProcedure");
//        TheResponse response = bStub.unaryProcedure(request);
//        System.out.println("...after calling unaryProcedure");
//        System.out.println("--> Response: " + response);
//
//        Iterator<TheResponse> respIterator;
//        System.out.println("...calling streamProcedure");
//        respIterator = bStub.streamProcedure(request);
//        System.out.println("...after calling streamProcedure");
//        TheResponse strResponse;
//        while(respIterator.hasNext()) {
//            strResponse = respIterator.next();
//            System.out.println("-->" + strResponse.getMessage());
//        }
//
//        System.out.println("...async calling unaryProcedure");
//        nonbStub.unaryProcedure(request, new UnaryObs());
//        System.out.println("...after async calling unaryProcedure");
//
//
//        System.out.println("...async calling streamProcedure");
//        nonbStub.streamProcedure(request, new UnaryObs());
//        promptEnterKey();
//        System.out.println("...after async calling streamProcedure");

        nonbStub.fibProcedure(fibRequest, new FibObs());
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

    private static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
