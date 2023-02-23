import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	private static final String EXCHANGE_NAME = "logs";
	private static final String ROUTING_KEY = "#my_route";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			EnvironementCaptor obj = EnvironementCaptor.getInstance(); 
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			out.flush();
			byte[] message = bos.toByteArray();
			System.out.println("Routing key : " + ROUTING_KEY + " ; message : " + obj.toString());

			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message);
			System.out.println(" [x] Sent '" + obj.toString() + "'");
		}
	}

}
