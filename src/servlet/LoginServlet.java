package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import entity.RandomNumber;
import entity.User;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type == null) {
			login(request, response);
		} else if (type.equals("login")) {
			login(request, response);
		} else if (type.equals("doLogin")) {
			doLogin(request, response);
		} else if (type.equals("randomImage")) {
			randomImage(request, response);
		}
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		
		String rand = request.getParameter("random");
		String sRand = (String) request.getSession().getAttribute("rand");
		try {
			PrintWriter out = response.getWriter();
			if (rand != null && sRand != null && rand.equals(sRand)) {
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				User user = new User();
				user.setUsername(userName);
				user.setPassword(password);

				UserDao userDao = new UserDao();
				boolean result = userDao.login(user);
				if (result) {
					request.getSession().setAttribute("userName", userName);
					out.print("success");
//					request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request, response);
				} else {
					out.print("用户名或密码错误！");
//					request.setAttribute("mes", "用户名或密码错误！");
//					request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
				}
			} else {
				out.print("验证码错误！");
//				request.setAttribute("mes", "验证码错误！");
//				request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber randomNumber = new RandomNumber();
		try {
			String rand= randomNumber.generateImage(response);
			request.getSession().setAttribute("rand",rand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
