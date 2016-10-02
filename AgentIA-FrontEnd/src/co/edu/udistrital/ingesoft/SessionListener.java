package co.edu.udistrital.ingesoft;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	public SessionListener() {
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		String sessionId = arg0.getSession().getId();
		
		System.out.println("SESION INICIADA: "+arg0+" ~ "+arg0.getSession()+" ~ "+sessionId);
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
//		HttpSession session = arg0.getSession();
//		String username = SearchCometServlet.users.get(session);
//		SearchCometServlet.sendBroadcast("[SYSTEM] " + username + " left.");
	}
}