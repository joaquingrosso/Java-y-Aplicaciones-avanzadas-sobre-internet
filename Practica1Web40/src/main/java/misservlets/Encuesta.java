package misservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class Encuesta
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Encuesta")
public class Encuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Integer> contadorMascotas = new HashMap<>();


    /**
     * Default constructor. 
     */
    public Encuesta() {
    	 contadorMascotas.put("perro", 0);
         contadorMascotas.put("gato", 0);
         contadorMascotas.put("hamster", 0);
         contadorMascotas.put("tortuga", 0);
         contadorMascotas.put("loro", 0);
         contadorMascotas.put("mono", 0);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String[] mascotasSeleccionadas = request.getParameterValues("mascotas");


        if (mascotasSeleccionadas != null) {
            for (String mascota : mascotasSeleccionadas) {
            	if (contadorMascotas.containsKey(mascota)) {
            		contadorMascotas.put(mascota, contadorMascotas.get(mascota) + 1);
            	}
            }
        }
		
		PrintWriter out=response.getWriter();	
		response.setContentType("text/html");
		
		
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Respuestas de la Encuesta</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Respuestas de la Encuesta</h1>");
        out.println("<p>Conteo de mascotas seleccionadas:</p>");
        out.println("<ul>");
        
        for (Map.Entry<String, Integer> entry : contadorMascotas.entrySet()) {
            out.println("<li>" + entry.getKey() + ": " + entry.getValue() + "</li>");
        }
        
        out.println("</ul>");
        out.println("<button onclick=\"window.location.href='mascotas.html';\">Volver</button>");
        out.println("</body>");
        out.println("</html>");
		out.close();
				
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
