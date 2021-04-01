package com.mrl.service;

import java.util.List;

import com.mrl.model.jsonplaceholder.User;
import com.mrl.util.JsonUtil;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(List<User> users, String topic) {

        users.forEach(user -> {
            sendMessage(user, topic);
        });

    }

    /**
     * The callback added is to prevent the sending thread to be blocked which would
     * slow down sending subsequent messages
     * 
     * @param user
     * @param topic
     */
    private void sendMessage(User user, String topic) {

        String message = JsonUtil.toJsonString(user);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, Long.toString(user.getId()),
                message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to [{}]", message, ex.getMessage());
            }
        });
    }

}
