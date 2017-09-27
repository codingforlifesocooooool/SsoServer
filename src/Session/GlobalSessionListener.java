package Session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Î¬»¤È«¾Ösession
 * @author hekai
 *
 */
public class GlobalSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	    System.out.println("create");
		GlobalSessions.addSession(se.getSession().getId(), se.getSession());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	    System.out.println("destory");
		GlobalSessions.delSession(se.getSession().getId());
	}

}
