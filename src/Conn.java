import java.io.IOException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

public class Conn {
	public static void main(String[] args) throws SmackException, IOException, XMPPException {
		
		ConnectionHelper conn = new ConnectionHelper();
		conn.connect("test", "test");
		
		conn.setCompanion("test2");
		
		conn.sendMessage("Howdy!!!!");
		conn.sendMessage("Howdy!!!!");
		conn.sendMessage("Howdy!!!!");
		
		conn.disconnect();
		
	}
}
