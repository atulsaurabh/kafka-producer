package saurabh.atul.kafka.producer.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/kafka/producer")
public class KafkaProducerController
{
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message)
    {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("personName",message);
        future.whenComplete((r, e) -> {
            System.out.println("Message sent successfully");
        });

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {}

        return ResponseEntity.ok("Done");

    }


}
