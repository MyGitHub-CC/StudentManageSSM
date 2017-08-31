package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BanJi;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreDao extends JdbcUtil{
	
	public List<Score> search(Score conditionScore, int begin) {
		List<Score> list = new ArrayList<Score>();
		try {
			creatConnection();
			String where = " where 1=1 ";
			if (conditionScore.getStudent().getName() != null 
				&& conditionScore.getStudent().getName() != "") {
				where += " and stuName = '" + conditionScore.getStudent().getName() + "'";
			}
			if (conditionScore.getStudent().getBanJi() != null
				&& conditionScore.getStudent().getBanJi().getId() > 0) {
				where += " and bjId = " + conditionScore.getStudent().getBanJi().getId();
			}
			if (conditionScore.getSubject() != null 
				&& conditionScore.getSubject().getId() > 0) {
				where += " and subId = " + conditionScore.getSubject().getId();
			}
			
			String sql = "SELECT * FROM v_stu_sub_score" + where + " order by stuId  LIMIT " + begin + ", 5";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getInt("scId"));
				score.setScore(rs.getInt("score"));
				
				Student student = new Student();
				student.setId(rs.getInt("stuId"));
				student.setName(rs.getString("stuName"));
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("bjId"));
				banJi.setName(rs.getString("bjName"));
				student.setBanJi(banJi);
				score.setStudent(student);
				
				Subject subject = new Subject();
				subject.setId(rs.getInt("subId"));
				subject.setName(rs.getString("subName"));
				score.setSubject(subject);
				
				list.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int searchCount(Score conditionScore) {
		int count = 0;
		try {
			creatConnection();
			String sql = "SELECT count(*) as n FROM v_stu_sub_score where 1=1 ";
			if (conditionScore.getStudent().getName() != null 
				&& conditionScore.getStudent().getName() != "") {
				sql += " and stuName = '" + conditionScore.getStudent().getName() + "'";
			}
			if (conditionScore.getStudent().getBanJi() != null
				&& conditionScore.getStudent().getBanJi().getId() > 0) {
				sql += " and bjId = " + conditionScore.getStudent().getBanJi().getId();
			}
			if (conditionScore.getSubject() != null 
				&& conditionScore.getSubject().getId() > 0) {
				sql += " and subId = " + conditionScore.getSubject().getId();
			}
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}

	public int update(Score score) {
		int result = 0;
		try {
			creatConnection();
			String sql = "update score set score = ? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, score.getScore());
			preparedStatement.setInt(2, score.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int add(Score score) {
		int result = 0;
		String sql = "insert into score(stu_id,sub_id,score) VALUE (?,?,?)";
		try {
			creatConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, score.getStudent().getId());
			preparedStatement.setInt(2, score.getSubject().getId());
			preparedStatement.setInt(3, score.getScore());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
