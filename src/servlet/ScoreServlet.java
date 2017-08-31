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
import dao.ScoreDao;
import dao.SubjectDao;

import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreServlet extends HttpServlet {
	ScoreDao scoreDao = new ScoreDao();
	BanJiDao banJiDao = new BanJiDao();
	SubjectDao subjectDao = new SubjectDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type != null) {
			if (type.equals("input")) {
				input(request, response);
			} else if (type.equals("showInput")) {
				showInput(request, response);
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

	public void search(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String name = "";
		if (request.getParameter("name") != null ) {
			if (!request.getParameter("name").equals("")){
				name = request.getParameter("name");
			}
		}
		int bjId = -1;
		if (request.getParameter("bjId") != null ) {
			if ( !request.getParameter("bjId").equals("")) {
				bjId =  Integer.parseInt(request.getParameter("bjId"));
			}
		}
		int subId = -1;
		if (request.getParameter("subId") != null ) {
			if ( !request.getParameter("subId").equals("")) {
				subId =  Integer.parseInt(request.getParameter("subId"));
			}
		}
		
		Score conditionScore = new Score();
		
		Student conditionStudent = new Student();
		conditionStudent.setName(name);
		BanJi banJi = new BanJi();
		banJi.setId(bjId);
		conditionStudent.setBanJi(banJi);
		conditionScore.setStudent(conditionStudent);
		
		Subject conditionSubject = new Subject();
		conditionSubject.setId(subId);
		conditionScore.setSubject(conditionSubject);
		
		int page = 1;
		int count = scoreDao.searchCount(conditionScore);
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
		int begin = (page - 1) * 5; 
		
		List<Score> scores = scoreDao.search(conditionScore, begin);
		List<BanJi> banJis = banJiDao.searchAll();
		List<Subject> subjects = subjectDao.searchAll();
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("banJis", banJis);
		request.setAttribute("subjects", subjects);
		request.setAttribute("conditionScore", conditionScore);
		request.setAttribute("scores", scores);
		try {
			request.getRequestDispatcher("WEB-INF/score/showScore.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void showInput(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String name = "";
		if (request.getParameter("name") != null ) {
			if (!request.getParameter("name").equals("")){
				name = request.getParameter("name");
			}
		}
		int bjId = -1;
		if (request.getParameter("bjId") != null ) {
			if ( !request.getParameter("bjId").equals("")) {
				bjId =  Integer.parseInt(request.getParameter("bjId"));
			}
		}
		int subId = -1;
		if (request.getParameter("subId") != null ) {
			if ( !request.getParameter("subId").equals("")) {
				subId =  Integer.parseInt(request.getParameter("subId"));
			}
		}
		
		Score conditionScore = new Score();
		
		Student conditionStudent = new Student();
		conditionStudent.setName(name);
		BanJi banJi = new BanJi();
		banJi.setId(bjId);
		conditionStudent.setBanJi(banJi);
		conditionScore.setStudent(conditionStudent);
		
		Subject conditionSubject = new Subject();
		conditionSubject.setId(subId);
		conditionScore.setSubject(conditionSubject);
		
		int page = 1;
		int count = scoreDao.searchCount(conditionScore);
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
		int begin = (page - 1) * 5; 
		
		List<Score> scores = scoreDao.search(conditionScore, begin);
		List<BanJi> banJis = banJiDao.searchAll();
		List<Subject> subjects = subjectDao.searchAll();
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("banJis", banJis);
		request.setAttribute("subjects", subjects);
		request.setAttribute("conditionScore", conditionScore);
		request.setAttribute("scores", scores);
		try {
			request.getRequestDispatcher("WEB-INF/score/inputScore.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void input(HttpServletRequest request, HttpServletResponse response) {
		int id = 0;
		if (request.getParameter("scoreId") != null) {
			id = Integer.parseInt(request.getParameter("scoreId"));
		}
		int stuId = -1;
		if (request.getParameter("stuId") != null) {
			stuId =  Integer.parseInt(request.getParameter("stuId"));
		}
		int subId = -1;
		if (request.getParameter("subId") != null) {
			subId = Integer.parseInt(request.getParameter("subId"));
		}
		int score = -1;
		if (request.getParameter("score") != null) {
			score = Integer.parseInt(request.getParameter("score"));
		}
		
		Score sc = new Score();
		sc.setId(id);
		sc.setScore(score);
		
		Student student = new Student();
		student.setId(stuId);
		sc.setStudent(student);
		
		Subject subject = new Subject();
		subject.setId(subId);
		sc.setSubject(subject);
		
		int result = 0;
		if (id == 0) {
			result = scoreDao.add(sc);
		} else {
			result = scoreDao.update(sc);
		}
		
		try {
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.print("录入成功！");
			} else {
				out.print("录入失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
