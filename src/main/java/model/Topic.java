package model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String topicId;
    private final String topicName;
    private final List<Subscriber> subscribers;

    public Topic(final String topicId, final String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(final Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }
}
