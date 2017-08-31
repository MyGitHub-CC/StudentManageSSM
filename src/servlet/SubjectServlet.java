package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import entity.Subject;

public class SubjectServlet extends HttpServlet{
	SubjectDao subjectDao = new SubjectDao();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type != null) {
			if(type.equals("search") ){
				search(request, response);
			} else if (type.equals("showAdd") ){
				showAdd(request, response);
			} else if(type.equals("add") ){
				add(request, response);
			} else if(type.equals("showModify") ){
				showModify(request, response);
			} else if(type.equals("modify") ){
				modify(request, response);
			} else if(type.equals("delete") ){
				delete(request, response);
			}
		} else {
			search(request, response);
		}
	}

	public void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/subject/add.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		Subject subject = new Subject();
		subject.setName(name);
		subjectDao.add(subject);
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('保存成功！'); window.location.href='subject?type=search';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Subject subject = subjectDao.searchById(id);
		request.setAttribute("subject", subject);
		try {
			request.getRequestDispatcher("WEB-INF/subject/modify.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Subject subject = new Subject();
		subject.setId(id);
		subject.setName(name);
		subjectDao.update(subject);
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('修改成功！'); window.location.href='subject?type=search';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = subjectDao.delete(id);
		if (result > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('删除成功！'); window.location.href='subject?type=search';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void search(HttpServletRequest request,
			HttpServletResponse response) {
		int page = 1;
		int count = subjectDao.searchCount();
		int maxPage = (count - 1) / 5 + 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			if (page < 1) {
				page = 1;
			}
			if (page >= maxPage) {
				page = maxPage;
			}
		}
		int begin = (page - 1) * 5; //计算出从数据库中的第几条记录开始查询
		
		String name = "";
		if (request.getParameter("name") != null){
			name = request.getParameter("name");
		}
		Subject sub = new Subject();
		sub.setName(name);
		List<Subject> subjects = subjectDao.searchByCondition(sub, begin);
		
		try {
			request.setAttribute("sub", sub);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("subjects", subjects);
			request.getRequestDispatcher("WEB-INF/subject/show.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
