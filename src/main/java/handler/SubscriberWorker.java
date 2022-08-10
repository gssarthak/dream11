package handler;

import model.Message;
import model.Subscriber;
import model.Topic;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final Subscriber subscriber;
    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }

    public SubscriberWorker(final Topic topic, final Subscriber subscriber, Message message) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.message = message;
    }

    @Override
    public void run() {
        synchronized (subscriber) {
            do {
                while (message == null) {
                    try {
                        subscriber.wait();
                    } catch(InterruptedException ex) {
                        System.out.println(ex.getStackTrace());
                    }
                }
                subscriber.consume(message);
                message = null;
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (subscriber) {
            subscriber.notify();
        }
    }
}
