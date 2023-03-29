import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class GrpcClient {

    private static ItemServiceGrpc.ItemServiceStub nonbStub;

    private static final Path CLIENT_PICTURES_PATH = Path.of("GrpcClient/src/main/resources/");
    public static void main(String[] args) throws InterruptedException, IOException {
        String address = "localhost"; //here we use service on the same host
        int port = 50051;
        ItemServiceGrpc.ItemServiceBlockingStub bStub;
        System.out.println("Running gRPC client...");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(address, port) .usePlaintext().build();

        nonbStub = ItemServiceGrpc.newStub(channel);
        bStub = ItemServiceGrpc.newBlockingStub(channel);


        int choice = -1;
        while(choice < 7) {
            choice = printMenu();
            switch (choice) {
                case 1 -> {
                    EmptyMessage itemsReadRequest = EmptyMessage.newBuilder().build();
                    nonbStub.itemsReadProcedure(itemsReadRequest, new ItemReadObserver());
                    promptEnterKey();
                }
                case 2 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter pesel: ");
                    final String pesel = scanner.nextLine();
                            ItemReadRequest itemReadRequest = ItemReadRequest.newBuilder()
                        .setPesel(pesel)
                        .build();
                    nonbStub.itemReadProcedure(itemReadRequest, new ItemReadObserver());
                    promptEnterKey();
                }
                case 3 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter pesel: ");
                    final String pesel = scanner.nextLine();
                    System.out.print("Enter name: ");
                    final String name = scanner.nextLine();
                    System.out.print("Enter filename: ");
                    final String filename = scanner.nextLine();
                    System.out.print("Enter age: ");
                    final int age = scanner.nextInt();

                    CountDownLatch latch = new CountDownLatch(1);
                    StreamObserver<ItemUploadRequest> streamObserver = nonbStub.itemUploadProcedure(new ItemUploadObserver(latch));

                     ItemUploadRequest itemUploadRequest = ItemUploadRequest.newBuilder()
                    .setItem(Item.newBuilder()
                        .setName(name)
                        .setPesel(pesel)
                        .setAge(age)
                        .setFileName(filename)
                        .build()
                     ).build();

                    streamObserver.onNext(itemUploadRequest);
                }
                case 4 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter pesel: ");
                    final String pesel = scanner.nextLine();

                    ItemReadRequest itemDeleteRequest = ItemReadRequest.newBuilder()
                        .setPesel(pesel)
                        .build();
                    bStub.itemDeleteProcedure(itemDeleteRequest);
                    System.out.println("Item deleted!");
                    promptEnterKey();
                }
                case 5 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter pesel: ");
                    final String pesel = scanner.nextLine();
                    System.out.print("Enter name: ");
                    final String name = scanner.nextLine();
                    System.out.print("Enter filename: ");
                    final String filename = scanner.nextLine();
                    System.out.print("Enter age: ");
                    final int age = scanner.nextInt();

                    CountDownLatch latch = new CountDownLatch(1);
                    StreamObserver<ItemUploadRequest> streamObserver = nonbStub.itemUploadProcedure(new ItemUploadObserver(latch));
                    Path path = CLIENT_PICTURES_PATH.resolve(filename);
                    InputStream inputStream = Files.newInputStream(path);
                    byte[] bytes = new byte[4096];
                    int size;

                    ItemUploadRequest itemUploadRequest = ItemUploadRequest.newBuilder()
                            .setItem(Item.newBuilder()
                                    .setName(name)
                                    .setPesel(pesel)
                                    .setAge(age)
                                    .setFileName(filename)
                                    .build()
                            ).build();

                    streamObserver.onNext(itemUploadRequest);

                    while((size = inputStream.read(bytes)) > 0) {
                        itemUploadRequest = ItemUploadRequest.newBuilder()
                                .setPicture(ByteString.copyFrom(bytes, 0, size))
                                .build();
                        streamObserver.onNext(itemUploadRequest);
                    }

                    System.out.println("Item added and image send!");
                    promptEnterKey();
                }
                case 6 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter pesel: ");
                    final String pesel = scanner.nextLine();

                    ItemPictureDownloadRequest itemPictureDownloadRequest = ItemPictureDownloadRequest.newBuilder()
                    .setPesel(pesel)
                    .build();

                    nonbStub.itemPictureDownloadProcedure(itemPictureDownloadRequest, new ItemPictureDownloadObserver());

                    System.out.println("Picture downloaded");
                    promptEnterKey();
                }
                default -> {
                    choice = 100;
                }
            }
            printEndlines();
        }




        channel.shutdown();
    }

    private static int printMenu() {
        System.out.println("1. Read All items");
        System.out.println("2. Read One item");
        System.out.println("3. Add item");
        System.out.println("4. Delete item");
        System.out.println("5. Add item with image");
        System.out.println("6. Download image");
        System.out.println("7. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choice: ");
        int result = scanner.nextInt();
        return result;
    }

    private static void printEndlines() {
        for(int i = 0; i < 30; i++) {
            System.out.println();
        }
    }


    private static class ItemUploadObserver implements StreamObserver<ItemUploadResponse> {

        private final CountDownLatch latch;

        public ItemUploadObserver(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onNext(ItemUploadResponse itemUploadResponse) {
            System.out.println("Message: " + itemUploadResponse.getMessage());
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onCompleted() {
            System.out.println("Done");
            this.latch.countDown();
        }
    }

    private static class ItemPictureDownloadObserver implements StreamObserver<ItemPictureDownloadResponse> {
        private static OutputStream os = null;

        public void onNext(ItemPictureDownloadResponse theResponse) {
            if(os == null) {
                try {
                    os = new FileOutputStream(CLIENT_PICTURES_PATH.resolve(theResponse.getFileName()).toFile());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                os.write(theResponse.getPicture().toByteArray());
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

    private static class ItemReadObserver implements StreamObserver<ItemReadResponse> {

        public void onNext(ItemReadResponse theResponse) {
            final Item item = theResponse.getItem();
            System.out.printf("Item: pesel(%s) name(%s) fileName(%s) age(%d) \n", item.getPesel(), item.getName(),
                    item.getFileName(), item.getAge());
        }
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }
        public void onCompleted() {
            System.out.println("item reading completed!");
        }
    }

    private static class ItemDeleteObserver implements StreamObserver<ItemReadResponse> {

        public void onNext(ItemReadResponse theResponse) {
            final Item item = theResponse.getItem();
            System.out.printf("Item with pesel(%s) deleted", item.getPesel());
        }
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }
        public void onCompleted() {
            System.out.println("item reading completed!");
        }
    }


    private static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
