import java.io.IOException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class ConnectionHelper {
	
	private AbstractXMPPConnection connection;
	private String companion;
	
	public void connect(String login, String pass) throws SmackException, IOException, XMPPException {
		// Create the configuration for this new connection
		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
				  
		configBuilder.setUsernameAndPassword(login, pass);
		configBuilder.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled);
		configBuilder.setServiceName("inquis-pc");
		configBuilder.setHost("localhost");
		configBuilder.setDebuggerEnabled(true);
				  
		connection = new XMPPTCPConnection(configBuilder.build());
		// Connect to the server
		connection.connect();
		// Log into the server
		connection.login();
	}
	
	public void sendMessage(String msg) throws NotConnectedException {
		// Assume we've created an XMPPConnection name "connection"._
		ChatManager chatmanager = ChatManager.getInstanceFor(connection);
		Chat newChat = chatmanager.createChat(companion + "@inquis-pc", new ChatMessageListener() {
			public void processMessage(Chat chat, Message message) {
				System.out.println("Received message: " + message);
			}
		});

		newChat.sendMessage(msg);
	}
	
	public void disconnect() {
		connection.disconnect();
	}
	
	public void setCompanion(String companion) {
		this.companion = companion;
	}
	
	public String getCompanion() {
		return companion;
	}
}
