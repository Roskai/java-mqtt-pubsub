import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.util.List;

public class LampeConsumer {

    private static final String LAMPE = "Lampe";

    public static void main(String[] argv)  throws Exception{
        
        LampadaireDao lampadaireDao = new LampadaireDao(); 
        List<Lampadaire> lampadaires = lampadaireDao.getAll(); 
        for(Lampadaire unLampadaire : lampadaires){
            receiveStateLampe(unLampadaire.getId());
        }
	}

		
    private static void receiveStateLampe(int id) throws Exception{
        
        ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("java-mqtt-pubsub-broker");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

        String topic = LAMPE+"/"+id; 

		channel.exchangeDeclare(topic, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, topic, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        System.out.println(topic);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {

			String message = new String(delivery.getBody(), "UTF-8");
		
			System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");			
		};

		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
		});

    }
}