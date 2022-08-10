package model;

public class Producer {
    private final String id;
    Queue queue;

    public Producer(String id, Queue queue) {
        this.id = id;
        this.queue = queue;
    }
    public String getId() {
        return id;
    }


    public void publish(final Topic topic, final Message message) {
        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        queue.processMessage(topic, message);
    }
}
