//package com.dark.temporal;
//
//import com.dark.temporal.workflow.OrderWorkflow;
//import io.temporal.client.WorkflowClient;
//import io.temporal.client.WorkflowOptions;
//import io.temporal.testing.TestWorkflowEnvironment;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.util.Assert;
//
//@SpringBootTest(classes = HelloSampleTest.Configuration.class)
//@ActiveProfiles("test")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@DirtiesContext
//public class HelloSampleTest {
//
//    @Autowired
//    ConfigurableApplicationContext applicationContext;
//
//    @Autowired
//    TestWorkflowEnvironment testWorkflowEnvironment;
//
//    @Autowired
//    WorkflowClient workflowClient;
//
//    @BeforeEach
//    void setUp() {
//        applicationContext.start();
//    }
//
//    @Test
//    public void testHello() {
//        OrderWorkflow workflow =
//                workflowClient.newWorkflowStub(
//                        OrderWorkflow.class,
//                        WorkflowOptions.newBuilder()
//                                .setTaskQueue("HelloOrderTaskQueue")
//                                .setWorkflowId("HelloSampleTest")
//                                .build());
//        String result = workflow.startOrder("");
//        Assert.notNull(result, "Greeting should not be null");
//        Assert.isTrue(result.equals("Hello Temporal User!"), "Invalid result");
//    }
//
//    @ComponentScan
//    public static class Configuration {
//    }
//}
