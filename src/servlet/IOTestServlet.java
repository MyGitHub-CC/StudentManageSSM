package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IOTestServlet extends HttpServlet{

	private static final long serialVersionUID = -2374554837515707858L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			
		} else if ("reader".equals(type)){
			
		} else if ("writer".equals(type)){
			
		}
	}
}
