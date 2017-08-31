package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BanJi;
import entity.Student;

public class StudentDao extends JdbcUtil{
	
	public List<Student> search(int begin) {
		List<Student> list = new ArrayList<Student>();
		try {
			creatConnection();
			String sql = "SELECT s.*,c.name as className FROM  student as s left join" +
					" banji as c on  s.class_id = c.id order by s.id  LIMIT " + begin + ", 5;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("class_id"));
				banJi.setName(rs.getString("className"));
				student.setBanJi(banJi);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int searchCount(Student conditionStudent) {
		int count = 0;
		try {
			creatConnection();
			String sql = "SELECT count(id) as n FROM student where 1=1 ";
			if (!conditionStudent.getName().equals("")) {
				sql += " and name like + '%" + conditionStudent.getName() + "%'";
			}
			if (!conditionStudent.getSex().equals("")) {
				sql += " and sex ='" + conditionStudent.getSex() + "'";
			}
			if (conditionStudent.getAge() > 0) {
				sql += " and age =" + conditionStudent.getAge();
			}
			if (conditionStudent.getBanJi().getId() > 0) {
				sql += " and class_id =" + conditionStudent.getBanJi().getId();
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

	public Student searchById(int id) {
		Student student = null;
		try {
			creatConnection();
			String sql = "SELECT s.*,c.name as className FROM student as s left join banji as c on s.class_id=c.id where s.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("class_id"));
				banJi.setName(rs.getString("className"));
				student.setBanJi(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return student;
	}

	public int add(Student student) {
		int result = 0;
		String sql = "insert into student(name,age,sex,class_id,photo) VALUE (?,?,?,?,?)";
		try {
			creatConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getSex());
			preparedStatement.setInt(4, student.getBanJi().getId());
			preparedStatement.setString(5, student.getPhoto());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int update(Student student) {
		int result = 0;
		try {
			creatConnection();
			String sql = "update student set name=?, age=? , sex=?,class_id=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getSex());
			preparedStatement.setInt(4, student.getBanJi().getId());
			preparedStatement.setInt(5, student.getId());
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
			String sql = "delete from student WHERE id =?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public List<Student> searchByCondition(Student conditionStudent,int begin) {
		List<Student> list = new ArrayList<Student>();
		try {
			creatConnection();
			String sql = "select s.*,c.name as className from student as s left join banji as c on s.class_id = c.id where 1=1 ";
			if (!conditionStudent.getName().equals("")) {
				sql += " and s.name like + '%" + conditionStudent.getName() + "%'";
			}
			if (!conditionStudent.getSex().equals("")) {
				sql += " and sex ='" + conditionStudent.getSex() + "'";
			}
			if (conditionStudent.getAge() > 0) {
				sql += " and age =" + conditionStudent.getAge();
			}
			if (conditionStudent.getBanJi().getId() > 0) {
				sql += " and class_id =" + conditionStudent.getBanJi().getId();
			}
			sql +=  " LIMIT " + begin + ", 5;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				conditionStudent = new Student();
				conditionStudent.setId(rs.getInt("id"));
				conditionStudent.setName(rs.getString("name"));
				conditionStudent.setAge(rs.getInt("age"));
				conditionStudent.setSex(rs.getString("sex"));
				conditionStudent.setPhoto(rs.getString("photo"));
				
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("class_id"));
				banJi.setName(rs.getString("className"));
				conditionStudent.setBanJi(banJi);
				list.add(conditionStudent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Student> searchAll() {
		List<Student> list = new ArrayList<Student>();
		creatConnection();
		String sql = "select s.*,c.name as className from student as s left join banji as c on s.class_id = c.id where 1=1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("class_id"));
				banJi.setName(rs.getString("className"));
				student.setBanJi(banJi);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
