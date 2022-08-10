package model;

import handler.TopicHandler;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class Queue {
    private final Map<String, TopicHandler> topicHandlers;

    public Queue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(final String topicName) {
        final Topic topic = new Topic(UUID.randomUUID().toString(), topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(final Subscriber subscriber, final Topic topic) {
        topic.addSubscriber(new Subscriber(subscriber.getId()));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void processMessage(final Topic topic, final Message message) {
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish(message)).start();
    }
}
