package com.lightbend.grpc.interop

import io.grpc.testing.integration2.TestServiceServer

object IoGrpcServer extends GrpcServer[TestServiceServer] {

  val label: String = "grpc-java server"

  val pendingCases =
    Set(
      "client_compressed_unary",
      "client_compressed_streaming")

  override def start() = {
    val server = new TestServiceServer
    if (server.useTls)
      println("\nUsing fake CA for TLS certificate. Test clients should expect host\n" +
        "*.test.google.fr and our test CA. For the Java test client binary, use:\n" +
        "--server_host_override=foo.test.google.fr --use_test_ca=true\n")

    server.start()
    server
  }

  override def stop(binding: TestServiceServer) = binding.stop()
}