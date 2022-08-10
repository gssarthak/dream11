package client;

import model.*;

public class QueueClient {
    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();
        final Topic topic1 = queue.createTopic("Topic1");
        final Topic topic2 = queue.createTopic("Topic2");
        final Producer p1 = new Producer("Producer1", queue);
        final Producer p2 = new Producer("Producer2", queue);
        final Subscriber sub1 = new Subscriber("Subscriber1");
        final Subscriber sub2 = new Subscriber("Subscriber2");
        final Subscriber sub3 = new Subscriber("Subscriber3");
        final Subscriber sub4 = new Subscriber("Subscriber4");
        final Subscriber sub5 = new Subscriber("Subscriber5");
        queue.subscribe(sub1, topic1);
        queue.subscribe(sub2, topic1);
        queue.subscribe(sub3, topic1);
        queue.subscribe(sub4, topic1);
        queue.subscribe(sub5, topic1);

        queue.subscribe(sub1, topic2);
        queue.subscribe(sub3, topic2);
        queue.subscribe(sub4, topic2);

        p1.publish(topic1, new Message("m1"));
        Thread.sleep(100);
        p1.publish(topic1, new Message("m2"));
        Thread.sleep(100);
        p2.publish(topic1, new Message("m3"));

        Thread.sleep(100);
        p1.publish(topic2, new Message("m4"));
        Thread.sleep(100);
        p2.publish(topic2, new Message("m5"));
    }
}

