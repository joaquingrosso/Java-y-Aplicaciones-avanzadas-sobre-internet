package misservlets.practica2;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Servlet implementation class LoginUsr
 */
public class LoginUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Hashtable<String, String> logins;

	
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        // Obtener los parámetros de inicialización del web.xml
        String[] userNames = config.getInitParameter("userNames").split(",");
        String[] passwords = config.getInitParameter("passwords").split(",");
        
        // Verificar que la cantidad de nombres de usuario y contraseñas sea la misma
        if (userNames.length != passwords.length) {
            throw new ServletException("Cantidad de nombres de usuario y contraseñas no coinciden");
        }
        
        // Inicializar la tabla logins con los nombres de usuario y contraseñas
        logins = new Hashtable<>();
        for (int i = 0; i < userNames.length; i++) {
            logins.put(userNames[i], passwords[i]);
        }
        
        //printLogins();
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
				
		if (isValidLogin(username, password)) {
			
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			response.sendRedirect(request.getContextPath() + "/Productos");
		} else {
			
			response.sendRedirect(request.getContextPath() + "/login.html");
		}
	}

	private boolean isValidLogin(String username, String password) {
		if (logins.get(username) != null) {
			return logins.get(username).equals(password);
		}
		return false;
	}

	private void printLogins() {
		System.out.println("Contenido del Hashtable logins:");
		Enumeration<String> keys = logins.keys();
		while (keys.hasMoreElements()) {
			String username = keys.nextElement();
			String password = logins.get(username);
			System.out.println("Usuario: " + username + ", Contraseña: " + password);
		}
	}

}
