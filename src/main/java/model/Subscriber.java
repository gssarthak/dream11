package model;

public class Subscriber {
    private final String id;

    public Subscriber(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void consume(Message message) {
        System.out.println(id + " received: " + message.getMessage());
    }
}
