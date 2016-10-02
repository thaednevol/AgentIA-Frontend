package co.edu.udistrital.ingesoft;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(displayName = "buscar", urlPatterns = { "/buscar" })
public class Buscar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {

		req.getRequestDispatcher("buscar.jsp").forward(req, res);

	}
}
