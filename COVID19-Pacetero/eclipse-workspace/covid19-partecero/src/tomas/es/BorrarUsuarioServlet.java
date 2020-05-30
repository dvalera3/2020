package tomas.es;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BorrarUsuarioServlet
 */
@WebServlet("/BorrarUsuarioServlet")
public class BorrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
        
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Mensaje desde m�todo doGet desde MyServlet</h1>");
        Connection con=null;
        try {
            boolean existeUsuario = false;
            //Guardamos los datos enviados desde index
            String usuario = request.getParameter("usuario");
            //Establecemos la conexion
            con = (Connection) Conexion.getConexion();
            System.out.println("la conexion es " + con);
            
            String consulta="delete from usuarios where usuario=?";
 		    ResultSet rs = null;
 			        //  CreateStatement pst = null;
 			PreparedStatement pst = (PreparedStatement) con.prepareStatement(consulta);
 			pst.setString(1, usuario);
            pst.execute();
            
            request.setAttribute("usuarioborrado", usuario);
            this.getServletContext().getRequestDispatcher("/bienvenidaadministrador.jsp").forward(request,response); 
            con.close();
            out.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException|NullPointerException ex) {
            out.println("Fallo SQL");
            request.setAttribute("errorMessage", (String)ex.getMessage());
            request.setAttribute("errorCause", ex.getCause());
            request.setAttribute("errorLocation", (String)this.getServletName());

            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
