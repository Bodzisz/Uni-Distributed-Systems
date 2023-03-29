import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: GrpcInterface.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServiceNameGrpc {

  private ServiceNameGrpc() {}

  public static final String SERVICE_NAME = "ServiceName";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<TheRequest,
      TheResponse> getUnaryProcedureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "unaryProcedure",
      requestType = TheRequest.class,
      responseType = TheResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<TheRequest,
      TheResponse> getUnaryProcedureMethod() {
    io.grpc.MethodDescriptor<TheRequest, TheResponse> getUnaryProcedureMethod;
    if ((getUnaryProcedureMethod = ServiceNameGrpc.getUnaryProcedureMethod) == null) {
      synchronized (ServiceNameGrpc.class) {
        if ((getUnaryProcedureMethod = ServiceNameGrpc.getUnaryProcedureMethod) == null) {
          ServiceNameGrpc.getUnaryProcedureMethod = getUnaryProcedureMethod =
              io.grpc.MethodDescriptor.<TheRequest, TheResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "unaryProcedure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TheRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TheResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceNameMethodDescriptorSupplier("unaryProcedure"))
              .build();
        }
      }
    }
    return getUnaryProcedureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<TheRequest,
      TheResponse> getStreamProcedureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamProcedure",
      requestType = TheRequest.class,
      responseType = TheResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<TheRequest,
      TheResponse> getStreamProcedureMethod() {
    io.grpc.MethodDescriptor<TheRequest, TheResponse> getStreamProcedureMethod;
    if ((getStreamProcedureMethod = ServiceNameGrpc.getStreamProcedureMethod) == null) {
      synchronized (ServiceNameGrpc.class) {
        if ((getStreamProcedureMethod = ServiceNameGrpc.getStreamProcedureMethod) == null) {
          ServiceNameGrpc.getStreamProcedureMethod = getStreamProcedureMethod =
              io.grpc.MethodDescriptor.<TheRequest, TheResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "streamProcedure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TheRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TheResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceNameMethodDescriptorSupplier("streamProcedure"))
              .build();
        }
      }
    }
    return getStreamProcedureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<FibRequest,
      FibResponse> getFibProcedureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "fibProcedure",
      requestType = FibRequest.class,
      responseType = FibResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<FibRequest,
      FibResponse> getFibProcedureMethod() {
    io.grpc.MethodDescriptor<FibRequest, FibResponse> getFibProcedureMethod;
    if ((getFibProcedureMethod = ServiceNameGrpc.getFibProcedureMethod) == null) {
      synchronized (ServiceNameGrpc.class) {
        if ((getFibProcedureMethod = ServiceNameGrpc.getFibProcedureMethod) == null) {
          ServiceNameGrpc.getFibProcedureMethod = getFibProcedureMethod =
              io.grpc.MethodDescriptor.<FibRequest, FibResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "fibProcedure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  FibRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  FibResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServiceNameMethodDescriptorSupplier("fibProcedure"))
              .build();
        }
      }
    }
    return getFibProcedureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServiceNameStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceNameStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceNameStub>() {
        @java.lang.Override
        public ServiceNameStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceNameStub(channel, callOptions);
        }
      };
    return ServiceNameStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServiceNameBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceNameBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceNameBlockingStub>() {
        @java.lang.Override
        public ServiceNameBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceNameBlockingStub(channel, callOptions);
        }
      };
    return ServiceNameBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServiceNameFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServiceNameFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServiceNameFutureStub>() {
        @java.lang.Override
        public ServiceNameFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServiceNameFutureStub(channel, callOptions);
        }
      };
    return ServiceNameFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  public static abstract class ServiceNameImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Remote procedures:
     * </pre>
     */
    public void unaryProcedure(TheRequest request,
        io.grpc.stub.StreamObserver<TheResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnaryProcedureMethod(), responseObserver);
    }

    /**
     */
    public void streamProcedure(TheRequest request,
        io.grpc.stub.StreamObserver<TheResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamProcedureMethod(), responseObserver);
    }

    /**
     */
    public void fibProcedure(FibRequest request,
        io.grpc.stub.StreamObserver<FibResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFibProcedureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUnaryProcedureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                TheRequest,
                TheResponse>(
                  this, METHODID_UNARY_PROCEDURE)))
          .addMethod(
            getStreamProcedureMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                TheRequest,
                TheResponse>(
                  this, METHODID_STREAM_PROCEDURE)))
          .addMethod(
            getFibProcedureMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                FibRequest,
                FibResponse>(
                  this, METHODID_FIB_PROCEDURE)))
          .build();
    }
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  public static final class ServiceNameStub extends io.grpc.stub.AbstractAsyncStub<ServiceNameStub> {
    private ServiceNameStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceNameStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceNameStub(channel, callOptions);
    }

    /**
     * <pre>
     * Remote procedures:
     * </pre>
     */
    public void unaryProcedure(TheRequest request,
        io.grpc.stub.StreamObserver<TheResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnaryProcedureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamProcedure(TheRequest request,
        io.grpc.stub.StreamObserver<TheResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamProcedureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fibProcedure(FibRequest request,
        io.grpc.stub.StreamObserver<FibResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getFibProcedureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  public static final class ServiceNameBlockingStub extends io.grpc.stub.AbstractBlockingStub<ServiceNameBlockingStub> {
    private ServiceNameBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceNameBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceNameBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Remote procedures:
     * </pre>
     */
    public TheResponse unaryProcedure(TheRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnaryProcedureMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<TheResponse> streamProcedure(
        TheRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamProcedureMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<FibResponse> fibProcedure(
        FibRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getFibProcedureMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The service definition.
   * </pre>
   */
  public static final class ServiceNameFutureStub extends io.grpc.stub.AbstractFutureStub<ServiceNameFutureStub> {
    private ServiceNameFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServiceNameFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServiceNameFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Remote procedures:
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<TheResponse> unaryProcedure(
        TheRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnaryProcedureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UNARY_PROCEDURE = 0;
  private static final int METHODID_STREAM_PROCEDURE = 1;
  private static final int METHODID_FIB_PROCEDURE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServiceNameImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ServiceNameImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UNARY_PROCEDURE:
          serviceImpl.unaryProcedure((TheRequest) request,
              (io.grpc.stub.StreamObserver<TheResponse>) responseObserver);
          break;
        case METHODID_STREAM_PROCEDURE:
          serviceImpl.streamProcedure((TheRequest) request,
              (io.grpc.stub.StreamObserver<TheResponse>) responseObserver);
          break;
        case METHODID_FIB_PROCEDURE:
          serviceImpl.fibProcedure((FibRequest) request,
              (io.grpc.stub.StreamObserver<FibResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ServiceNameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServiceNameBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrpcAppProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ServiceName");
    }
  }

  private static final class ServiceNameFileDescriptorSupplier
      extends ServiceNameBaseDescriptorSupplier {
    ServiceNameFileDescriptorSupplier() {}
  }

  private static final class ServiceNameMethodDescriptorSupplier
      extends ServiceNameBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ServiceNameMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServiceNameGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServiceNameFileDescriptorSupplier())
              .addMethod(getUnaryProcedureMethod())
              .addMethod(getStreamProcedureMethod())
              .addMethod(getFibProcedureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
