package com.mqtest.springbootmq.topic;


import com.mqtest.MqApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes=MqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    private TopicSend send;

    @org.junit.Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            send.send();

            Thread.sleep(1000);
        }
    }
}
