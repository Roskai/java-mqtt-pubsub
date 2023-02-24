import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import java.util.List;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import com.google.gson.Gson; 

import java.io.IOException;



public class Consumer extends Exception{

	private static final String TOPIC_ENV = "Env";
	private static final String TOPIC_LAMPE = "Lampe";
	private static final String ROUTING_KEY = "#my_route";

	

	public static void main(String[] argv) throws Exception {
	
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("java-mqtt-pubsub-broker");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(TOPIC_ENV, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, TOPIC_ENV, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		LampadaireDao lampadaireDao = new LampadaireDao(); 

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
// Désérialise les données reçues pour récupérer l'objet EnvironmentCaptor
			Gson gson = new Gson();

			String message = new String(delivery.getBody(), "UTF-8");
			EnvironmentCaptor nosCapteurs = gson.fromJson(message, EnvironmentCaptor.class); 

			System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + nosCapteurs.toString() + "'");

			boolean state = Serveur.changeLampadaireState(nosCapteurs.getHour(), nosCapteurs.getBrightness());
		
			lampadaireDao.setAllState(state); 
		
		
			List<Lampadaire> lampadaires = lampadaireDao.getAll(); 

			for(Lampadaire unLampadaire : lampadaires ){
				try {
					sendStateLampadaire(unLampadaire);
				}catch(Exception e){
					System.out.println("erreur sentStateLampadaire");
				}
				
			}
		};

		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
		});

		
	}


	private static void sendStateLampadaire(Lampadaire lampadaire) throws Exception{
	
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("java-mqtt-pubsub-broker");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {


			String topic = TOPIC_LAMPE + "/"+lampadaire.getId(); 

			channel.exchangeDeclare(topic, "fanout");

			
			String message = lampadaire.getState() ? "true" : "false";

			System.out.println("Routing key : " + ROUTING_KEY + " ; message : " + message);
			System.out.println(topic);

			channel.basicPublish(topic, ROUTING_KEY, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
			
		}
	}
}
