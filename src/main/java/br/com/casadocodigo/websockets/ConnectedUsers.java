package br.com.casadocodigo.websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ConnectedUsers {
	
	private Logger LOGGER = LoggerFactory.getLogger(ConnectedUsers.class);
	
	private Set<Session> remoteUsers = new HashSet<>();
	
	public void add(Session remoteUser) {
		this.remoteUsers.add(remoteUser);
		LOGGER.debug("Remote user added..");
	}

	public void send(String message) {
		for (Session remoteUser : this.remoteUsers) {
			if (remoteUser.isOpen()) {
				try {
					remoteUser.getBasicRemote().sendText(message);
				} catch (IOException e) {
					LOGGER.error("Não foi possível enviar mensagem para um cliente.");
				}
			}
		}
	}

	public boolean remove(Session session) {
		return this.remoteUsers.remove(session);
	}
}
