package br.com.casadocodigo.websockets;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/channel/sales")
public class SalesEndpoint {
	
	@Inject
	private ConnectedUsers remoteUsers;
	
	@OnOpen
	public void onOpen(Session remoteUser) {
		this.remoteUsers.add(remoteUser);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println(this.remoteUsers.remove(session));
		System.out.println(closeReason.getCloseCode());
	}
}
