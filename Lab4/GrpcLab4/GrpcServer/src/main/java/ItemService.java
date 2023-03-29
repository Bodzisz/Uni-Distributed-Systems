import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.checkerframework.framework.qual.LiteralKind;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemService extends ItemServiceGrpc.ItemServiceImplBase {

    private Set<ServerItem> items;

    private static final String SUCCESS_MESSAGE = "SUCCESS";
    private static final String ERROR_MESSAGE = "ERROR";

    private static final Path SERVER_RESOURCES_PATH = Path.of("GrpcServer/src/main/resources/");

    public ItemService() {
        this.items = new HashSet<>();
        this.items.add(new ServerItem("1", "Marek Nowak", 32, "img1.png"));
        this.items.add(new ServerItem("2", "Mariusz Pudzianowski", 45, "img2.png"));
        this.items.add(new ServerItem("3", "Robert Maklowicz", 22, "img3.png"));
    }

    public StreamObserver<ItemUploadRequest> itemUploadProcedure(StreamObserver<ItemUploadResponse> responseObserver) {
        return new StreamObserver<ItemUploadRequest>() {
            OutputStream writer;

            @Override
            public void onNext(ItemUploadRequest itemUploadRequest) {
                try {
                    if(itemUploadRequest.hasItem()) {
                        items.add(new ServerItem(itemUploadRequest.getItem().getPesel(),
                                itemUploadRequest.getItem().getName(),
                                itemUploadRequest.getItem().getAge(),
                                itemUploadRequest.getItem().getFileName()));
                        writer = Files.newOutputStream(SERVER_RESOURCES_PATH.resolve(itemUploadRequest.getItem().getFileName()),
                                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    }
                    else {
                        if(writer != null) {
                            writer.write(itemUploadRequest.getPicture().toByteArray());
                            writer.flush();
                        }
                        else {
                            System.out.println("Uninitialized writing to file attempt!");
                        }
                    }
                } catch (IOException e) {
                    this.onError(e);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error occured during upload!");
                throwable.printStackTrace();

                completeWithMessage(ERROR_MESSAGE);
            }

            @Override
            public void onCompleted() {
                completeWithMessage(SUCCESS_MESSAGE);
            }

            private void completeWithMessage(final String message) {
                closeFile(writer);
                System.out.println("Success! File was uploaded.");
                writer = null;
                ItemUploadResponse response = ItemUploadResponse.newBuilder()
                        .setMessage(message)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }

            private void closeFile(OutputStream writer) {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void itemPictureDownloadProcedure(ItemPictureDownloadRequest request,
                                             StreamObserver<ItemPictureDownloadResponse> responseObserver) {
        System.out.println("...sending file start");
        final String fileName = this.items.stream()
                .filter(item -> item.getPesel().equals(request.getPesel()))
                .map(ServerItem::getFileName)
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Item not found"));

        final int CHUNK_SIZE = 250_000;
        try(InputStream is = new FileInputStream(SERVER_RESOURCES_PATH.resolve(fileName).toFile())) {
            while(is.available() > 0) {
                System.out.println("Sending chunk");
                byte[] bytes = is.readNBytes(CHUNK_SIZE);
                ItemPictureDownloadResponse resp = ItemPictureDownloadResponse.newBuilder()
                        .setPicture(ByteString.copyFrom(bytes))
                        .setFileName(fileName)
                        .setMessage(fileName)
                        .build();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                responseObserver.onNext(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        responseObserver.onCompleted();
        System.out.println("...sending file end");
    }

    public void itemReadProcedure(ItemReadRequest request,
                                             StreamObserver<ItemReadResponse> responseObserver) {
        System.out.println("Reading single item start");
        final ServerItem item = this.items.stream()
                .filter(i -> i.getPesel().equals(request.getPesel()))
                .findFirst()
                .orElseThrow(() ->new RuntimeException("Item not found"));

        ItemReadResponse itemReadResponse = ItemReadResponse.newBuilder()
                        .setItem(Item.newBuilder()
                                .setPesel(item.getPesel())
                                .setAge(item.getAge())
                                .setFileName(item.getFileName())
                                .setName(item.getName())
                                .build())
                        .build();

        responseObserver.onNext(itemReadResponse);
        responseObserver.onCompleted();
        System.out.println("Reading single item end");
    }

    public void itemsReadProcedure(EmptyMessage request,
                                  StreamObserver<ItemReadResponse> responseObserver) {
        System.out.println("Reading items start");

        for(final ServerItem item : items) {
            ItemReadResponse itemReadResponse = ItemReadResponse.newBuilder()
                    .setItem(Item.newBuilder()
                            .setPesel(item.getPesel())
                            .setAge(item.getAge())
                            .setFileName(item.getFileName())
                            .setName(item.getName())
                            .build())
                    .build();

            responseObserver.onNext(itemReadResponse);
        }
        responseObserver.onCompleted();
        System.out.println("Reading items end");
    }

    public void itemDeleteProcedure(ItemReadRequest request,
                                   StreamObserver<EmptyMessage> responseObserver) {
        System.out.println("Deleting item start");

        this.items = this.items.stream()
                .filter(i -> !i.getPesel().equals(request.getPesel()))
                .collect(Collectors.toSet());

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
        System.out.println("Deleting item end");
    }
}
