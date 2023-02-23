import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import com.google.gson.Gson; 


public class Consumer {

	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
// Désérialise les données reçues pour récupérer l'objet EnvironmentCaptor
			Gson gson = new Gson();

			String message = new String(delivery.getBody(), "UTF-8");
			EnvironmentCaptor nosCapteurs = gson.fromJson(message, EnvironmentCaptor.class); 

			System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + nosCapteurs.toString() + "'");
		};

		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
		});
	}

}
