package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sv_Msg extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		String idPere = request.getParameter("idPere");
		String msg = request.getParameter("msg");
		response.setContentType("json");
		response.getWriter().println(services.Messages.send_msg(id_user, idPere, msg).toString());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		String idPere = request.getParameter("idPere");
		response.setContentType("json");
		response.getWriter().println(services.Messages.get_msg(id_user, idPere).toString());
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_msg = request.getParameter("id_msg");
		response.setContentType("json");
		response.getWriter().println(services.Messages.del_msg(id_msg).toString());
	}
}
