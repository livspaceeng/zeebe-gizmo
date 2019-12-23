package com.livspace.zeebe.gizmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.zeebe.spring.client.EnableZeebeClient;

/** @author Ankit Agrawal */
@SpringBootApplication
@EnableZeebeClient
public class ZeebeGizmoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZeebeGizmoApplication.class, args);
  }
}
