package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class SubjectDao extends JdbcUtil{

	public List<Subject> searchAll() {
		List<Subject> list = new ArrayList<Subject>();
		try {
			creatConnection();
			String sql = "SELECT * FROM subject;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<Subject> search(int begin) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			creatConnection();
			String sql = "SELECT * FROM subject limit " + begin + ", 5;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int searchCount() {
		int count = 0;
		try {
			creatConnection();
			String sql = "SELECT count(id) as n FROM subject;";
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

	public int add(Subject subject) {
		int result = 0;
		String sql = "insert into subject(name) VALUE (?)";
		try {
			creatConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject.getName());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public Subject searchById(int id) {
		Subject subject = null;
		try {
			creatConnection();
			String sql = "SELECT * FROM subject where id=" + id + ";";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return subject;
	}

	public int update(Subject subject) {
		int result = 0;
		try {
			creatConnection();
			String sql = "update subject set name=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject.getName());
			preparedStatement.setInt(2, subject.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int delete(int id) {
		int result = 0;
		try {
			creatConnection();
			String sql = "delete from subject WHERE id =" + id;
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Subject> searchByCondition(Subject conditionSubject, int begin) {
		 List<Subject> list = new ArrayList<Subject>();
		 try {
			 creatConnection();
			 String sql = "select * from subject where 1=1 ";
			 if (!conditionSubject.getName().equals("")) {
				 sql += " and name like + '%" + conditionSubject.getName() + "%'";
			 }
			 sql += "  limit " + begin + ", 5;";
			 preparedStatement = connection.prepareStatement(sql);
			 rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				conditionSubject = new Subject();
				conditionSubject.setId(rs.getInt("id"));
				conditionSubject.setName(rs.getString("name"));
				list.add(conditionSubject);
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 close();
		 }
		 return list;
	}
	
	
	public List<Subject> searchByCondition(int class_id) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			creatConnection();
			String sql = "SELECT * FROM subject WHERE id not in " +
					"(SELECT sub_id FROM m_bj_sub WHERE bj_id = " + class_id + ")";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Subject> searchByBanJi(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			creatConnection();
			String sql = "SELECT sub.* FROM subject as sub LEFT JOIN m_bj_sub as m on sub.id=m.sub_id where m.bj_id="
					+ bjId;
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

}
