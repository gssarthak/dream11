package handler;

import handler.SubscriberWorker;
import model.Message;
import model.Subscriber;
import model.Topic;

import java.util.*;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public TopicHandler(final Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    public void publish(Message message) {
        for (Subscriber subscriber:topic.getSubscribers()) {
            startSubsriberWorker(subscriber, message);
        }
    }

    public void startSubsriberWorker(final Subscriber subscriber, Message message) {
        final String subscriberId = subscriber.getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, subscriber, message);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.setMessage(message);
        subscriberWorker.wakeUpIfNeeded();
    }
}
