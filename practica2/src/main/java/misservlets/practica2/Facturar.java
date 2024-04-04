package misservlets.practica2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class Facturar
 */
public class Facturar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facturar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Map<String, Integer> cantidades = (Map<String, Integer>) session.getAttribute("cantidades");

        System.out.println("En facturar");
        for (Map.Entry<String, Integer> entry : cantidades.entrySet()) {
            String golosina = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("Golosina: " + golosina + ", Cantidad: " + cantidad);
        }
        
        if (cantidades != null) {
            // Procesar los datos recibidos del formulario
            for (String golosina : cantidades.keySet()) {
                String cantidadParamName = "cantidad_" + golosina; // Construir el nombre del parámetro
                int cantidad = Integer.parseInt(request.getParameter(cantidadParamName));
                cantidades.put(golosina, cantidad); // Actualizar la cantidad en el mapa
            }

            // Guardar el mapa actualizado en la sesión
            session.setAttribute("cantidades", cantidades);
        }

        // Redirigir al servlet Productos para que el usuario pueda seguir comprando
        response.sendRedirect(request.getContextPath() + "/Productos");
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> cantidades = (Map<String, Integer>) session.getAttribute("cantidades");

        if (cantidades == null) {
            response.sendRedirect(request.getContextPath() + "/Productos"); // Redirigir si no hay datos en la sesión
            return;
        }

        double totalGeneral = 0.0;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Factura</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Factura</h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Nombre Golosina</th><th>Cantidad</th><th>Precio Total</th></tr>");
        for (String golosina : cantidades.keySet()) {
            int cantidad = cantidades.get(golosina);
            double precioUnitario = obtenerPrecioUnitario(golosina); // Obtener precio unitario de la golosina
            double precioTotal = cantidad * precioUnitario;
            totalGeneral += precioTotal;
            out.println("<tr>");
            out.println("<td>" + golosina + "</td>");
            out.println("<td>" + cantidad + "</td>");
            out.println("<td>" + precioTotal + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<p>Total General: " + totalGeneral + "</p>");
        out.println("<br>");
        out.println("<a href=\"Productos\">Seguir comprando</a>"); // Enlace para seguir comprando
        out.println("<br>");
        out.println("<a href=\"TerminarSesion\">Salir</a>"); // Enlace para terminar la sesión
        out.println("</body>");
        out.println("</html>");
    }

    // Método para obtener el precio unitario de una golosina (a implementar)
    private double obtenerPrecioUnitario(String golosina) {
        // Aquí deberías implementar la lógica para obtener el precio unitario de la golosina
        // Esto puede ser desde una base de datos, un archivo de configuración, o cualquier otro medio
        // Por simplicidad, asumimos que siempre es 1.0 en este ejemplo
        return 1.0;
    }
	

}
