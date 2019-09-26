package com.happycoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.happycoding.model.Provider;

@Service
public class KafkaConsumer {

	@Autowired
	private MongoTemplate mongoTemplate;

	@KafkaListener(topics = "#{'${topic.id}'}", containerFactory = "providerKafkaListenerContainerFactory")
	public void consumeJson(Provider provider) {
		// @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
		// @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
		// @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
		// System.out.println("Consumed JSON key:" + key);
		// System.out.println("Consumed JSON partition:" + partition);
		// System.out.println("Consumed JSON topic:" + topic);
		provider.setId(provider.getId() + 1000);
		mongoTemplate.save(provider);
		System.out.println("Consumed JSON Message:" + provider);
	}
}