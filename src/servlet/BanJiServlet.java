package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BanJiDao;
import dao.SubjectDao;
import entity.BanJi;
import entity.Subject;

public class BanJiServlet extends HttpServlet{
	BanJiDao banJiDao = new BanJiDao();
	SubjectDao subjectDao = new SubjectDao();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			searchClass(request, response);
		} else if (type.equals("searchClass")) {
			searchClass(request, response);
		} else if (type.equals("showAddClass")) {
			showAddClass(request, response);
		} else if (type.equals("addClass")) {
			addClass(request, response);
		} else if (type.equals("showModifyClass")) {
			showModifyClass(request, response);
		} else if (type.equals("modifyClass")) {
			modifyClass(request, response);
		} else if (type.equals("deleteClass")) {
			deleteClass(request, response);
		} else if (type.equals("showSubject")) {
			showSubject(request, response);
		} else if (type.equals("addSubject")) {
			addSubject(request, response);
		} else if (type.equals("deleteSubject")) {
			deleteSubject(request, response);
		}
	}

	private void addSubject(HttpServletRequest request,
			HttpServletResponse response) {
		int subId = Integer.parseInt(request.getParameter("subid"));
		int bjId = Integer.parseInt(request.getParameter("bjId"));
		String bjName = request.getParameter("bjName");
		int result = banJiDao.addSubject(bjId,subId);
		BanJi banJi = banJiDao.showSubject(bjId);
		banJi.setId(bjId);
		banJi.setName(bjName);
		List<Subject> list = subjectDao.searchByCondition(bjId);
		request.setAttribute("banJi", banJi);
		request.setAttribute("list", list);
			try {
				if (result > 0) {
					request.getRequestDispatcher("WEB-INF/banji/showSubject.jsp").forward(request, response);
				}else {
					System.out.println("添加失败");
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	private void deleteSubject(HttpServletRequest request,
			HttpServletResponse response) {
		int subId = Integer.parseInt(request.getParameter("subId"));
		int bjId = Integer.parseInt(request.getParameter("bjId"));
		String bjName = request.getParameter("bjName");
		int result = banJiDao.deleteSubject(bjId,subId);
			BanJi banJi = banJiDao.showSubject(bjId);
			banJi.setId(bjId);
			banJi.setName(bjName);
			List<Subject> list = subjectDao.searchByCondition(bjId);
			request.setAttribute("banJi", banJi);
			request.setAttribute("list", list);
			try {
				if (result > 0) {
					request.getRequestDispatcher("WEB-INF/banji/showSubject.jsp").forward(request, response);
				}else {
					System.out.println("删除失败");
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	private void showSubject(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		BanJi banJi = banJiDao.showSubject(id);
		banJi.setId(id);
		banJi.setName(name);
		List<Subject> list = subjectDao.searchByCondition(id);
		
		request.setAttribute("banJi", banJi);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("WEB-INF/banji/showSubject.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAddClass(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/banji/addClass.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addClass(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		BanJi banJi = new BanJi();
		banJi.setName(name);
		banJiDao.add(banJi);
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('保存成功！'); window.location.href='banji?type=searchClass';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showModifyClass(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		BanJi banJi = banJiDao.searchById(id);
		request.setAttribute("banji", banJi);
		try {
			request.getRequestDispatcher("WEB-INF/banji/modifyClass.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modifyClass(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		BanJi banJi = new BanJi();
		banJi.setId(id);
		banJi.setName(name);
		banJiDao.update(banJi);
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('修改成功！'); window.location.href='banji?type=searchClass';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteClass(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = banJiDao.delete(id);
		if (result > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('删除成功！'); window.location.href='banji?type=searchClass';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void searchClass(HttpServletRequest request,
			HttpServletResponse response) {
		String banJiName = "";
		if (request.getParameter("name") != null && !request.getParameter("name").equals("")){
			banJiName = request.getParameter("name");
		}
		BanJi banJi = new BanJi();
		banJi.setName(banJiName);
		int page = 1;
		int count = banJiDao.searchCount(banJi);
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
		BanJi bj = new BanJi();
		bj.setName(name);
		List<BanJi> list = banJiDao.searchByCondition(bj, begin);
		
		request.setAttribute("bj", bj);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("WEB-INF/banji/showClass.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

/*	public void showClass(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			int page = 1;
			int count = banJiDao.searchCount();
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
			List<BanJi> list = banJiDao.search(begin);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.getRequestDispatcher("WEB-INF/banji/showclass.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
