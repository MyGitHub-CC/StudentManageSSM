package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BanJiDao;
import dao.StudentDao;
import entity.BanJi;
import entity.Student;


public class StudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4522719181175777622L;
	
	StudentDao studentDao = new StudentDao();
	BanJiDao banJiDao = new BanJiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		String type = request.getParameter("type");
		if (type != null) {
			if (type.equals("showAdd")) {
				showAdd(request, response);
			} else if (type.equals("add")) {
				add(request, response);
			} else if (type.equals("showModify")) {
				showModify(request, response);
			} else if (type.equals("modify")) {
				modify(request, response);
			} else if (type.equals("delete")) {
				delete(request, response);
			} else if (type.equals("search")) {
				search(request, response);
			}
		} else {
			search(request, response);
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		List<BanJi> banJis = banJiDao.searchAll();
		try {
			request.setAttribute("banJis", banJis);
			request.getRequestDispatcher("WEB-INF/student/add.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "/photo";
		FileItemFactory factory = new DiskFileItemFactory();//为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		String name = "";
		int age = 0;
		String sex = "";
		int id = 0;
		String fileName = "";
		for (FileItem item : items) {
			String fieldName = item.getFieldName();
			if (fieldName != null) {
				if (fieldName.equals("photo")) {
					fileName = item.getName();
					UUID uuid = UUID.randomUUID();
					int index = fileName.lastIndexOf(".");
					fileName = uuid + fileName.substring(index);
					String url= uploadPath + "/" +fileName ;
					File savedFile = new File(url);
					item.write(savedFile);
				} else if (fieldName.equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
				} else if (fieldName.equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
				} else if (fieldName.equals("age")) {
					age = Integer.parseInt(item.getString());
				} else if (fieldName.equals("className")) {
					id = Integer.parseInt(item.getString());
				}
			}
		}
		
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		student.setSex(sex);
		student.setPhoto(fileName);
		BanJi banJi = new BanJi();
		banJi.setId(id);
		student.setBanJi(banJi);
		studentDao.add(student);
		PrintWriter out = response.getWriter();
		out.print("<script>alert('保存成功！'); window.location.href='student?type=search';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDao.searchById(id);
		List<BanJi> banJis = banJiDao.searchAll();
		try {
			request.setAttribute("student", student);
			request.setAttribute("banJis", banJis);
			request.getRequestDispatcher("WEB-INF/student/modify.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String sex = request.getParameter("sex");
		int banJi_id = Integer.parseInt(request.getParameter("className"));
		
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setAge(age);
		student.setSex(sex);
		
		BanJi banJi = new BanJi();
		banJi.setId(banJi_id);
		student.setBanJi(banJi);
		student.setSex(sex);
		studentDao.update(student);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>alert('修改成功！'); window.location.href='student?type=search';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int result = studentDao.delete(id);
		if (result > 0) {
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('删除成功！'); window.location.href='student?type=search';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String name = "";
		int age = -1;
		String sex = "";
		int banJi_id = -1;
		if (request.getParameter("name") != null
				&& request.getParameter("age") != null
				&& request.getParameter("sex") != null
				&& request.getParameter("className") != null) {
			if (!request.getParameter("name").equals("")){
				name = request.getParameter("name");
			}
			if ( !request.getParameter("sex").equals("")) {
				sex = request.getParameter("sex");
			}
			if ( !request.getParameter("age").equals("")) {
				try {
					age = Integer.parseInt(request.getParameter("age"));
				} catch (Exception e) {
					System.out.println("年龄输入有误！");
				}
			}
			if ( !request.getParameter("className").equals("")) {
				banJi_id =  Integer.parseInt(request.getParameter("className"));
			}
		}
		
		Student stu = new Student();
		stu.setName(name);
		stu.setAge(age);
		stu.setSex(sex);
		BanJi banJi = new BanJi();
		banJi.setId(banJi_id);
		stu.setBanJi(banJi);
		int page = 1;
		int count = studentDao.searchCount(stu);
		int maxPage = (count - 1) / 5 + 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			if (page < 1) {
				page = 1;
			}
			if (page > maxPage) {
				page = maxPage;
			}
		}
		int begin = (page - 1) * 5; 
		
		List<Student> stus = studentDao.searchByCondition(stu,begin);
		List<BanJi> banJis = banJiDao.searchAll();
		
		request.setAttribute("stu", stu);
		request.setAttribute("stus", stus);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("banJis", banJis);
		try {
			request.getRequestDispatcher("WEB-INF/student/showStudent.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
//	public void show(HttpServletRequest request, HttpServletResponse response) {
//	response.setContentType("text/html;charset=UTF-8");
//	try {
//		int page = 1;//默认第1页开始查询
//		Student stu = new Student();
//		stu.setName("");
//		stu.setAge(-1);
//		stu.setSex("");
//		BanJi banJi = new BanJi();
//		banJi.setId(-1);
//		stu.setBanJi(banJi);
//		
//		int count = studentDao.searchCount(stu); //查询一共有多少学生
//		int maxPage = (count - 1) / 5 + 1; // 计算出最大页数
//		if (request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//			if (page < 1) {
//				page = 1;
//			}
//			if (page >= maxPage) {
//				page = maxPage;
//			}
//		}
//		int begin = (page - 1) * 5; //计算出从数据库中的第几条记录开始查询
//		List<Student> stus = studentDao.search(begin);
//		List<BanJi> banJis = banJiDao.searchAll();
//		request.setAttribute("stus", stus);
//		request.setAttribute("banJis", banJis);
//		request.setAttribute("stu", stu);
//		request.setAttribute("page", page);
//		request.setAttribute("maxPage", maxPage);
//		request.getRequestDispatcher("WEB-INF/student/showStudent.jsp").forward(
//				request, response);
//	} catch (ServletException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}


}
