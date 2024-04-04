package misservlets.practica2;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class Productos
 */
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] nombresGolosinas;
    private double[] preciosUnitarios;
    private int[] cantidades;

    
    
    public Productos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        
        nombresGolosinas = config.getInitParameter("nombres").split(",");
        String[] auxPrecios = config.getInitParameter("precios").split(",");
        preciosUnitarios = new double[auxPrecios.length];
        
        for (int i = 0; i < auxPrecios.length; i++) {
        	preciosUnitarios[i] = Double.parseDouble(auxPrecios[i]);
        }
        
    }   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
        // Obtener o inicializar las cantidades de golosinas en la sesión del usuario
        Map<String, Integer> cantidades = (Map<String, Integer>) session.getAttribute("cantidades");
        if (cantidades == null) {
            cantidades = new HashMap<>();
            for (String golosina : nombresGolosinas) {
                cantidades.put(golosina, 0);
            }
            session.setAttribute("cantidades", cantidades);
        }
        
        System.out.println("En productos");
        for (Map.Entry<String, Integer> entry : cantidades.entrySet()) {
            String golosina = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("Golosina: " + golosina + ", Cantidad: " + cantidad);
        }
		
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Productos</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Lista de Golosinas</h2>");
        out.println("<form action=\"Facturar\" method=\"post\">");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Nombre</th><th>Precio Unitario</th><th>Cantidad</th></tr>");
        for (int i = 0; i < nombresGolosinas.length; i++) {
            String golosina = nombresGolosinas[i];
            double precio = preciosUnitarios[i];
            int cantidad = cantidades.get(golosina); // Obtener la cantidad actual de la sesión
            out.println("<tr>");
            out.println("<td>" + golosina + "</td>");
            out.println("<td>" + precio + "</td>");
            out.println("<td><input type=\"text\" name=\"cantidad_" + i + "\" value=\"" + cantidad + "\"></td>"); 
            //un guion bajo para separar "cantidad" y el índice i en el nombre del campo de entrada
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<input type=\"submit\" value=\"Facturar\">");
        out.println("</form>");
        out.println("<br>");
        out.println("<a href=\"TerminarSesion\">Salir</a>"); // Enlace para terminar la sesión
        out.println("</body>");
        out.println("</html>");
	}

	

}
