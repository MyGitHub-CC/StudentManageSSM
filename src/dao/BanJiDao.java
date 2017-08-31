package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BanJi;
import entity.Subject;

public class BanJiDao extends JdbcUtil{

	public List<BanJi> searchAll() {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			creatConnection();
			String sql = "SELECT * FROM banji;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("id"));
				banJi.setName(rs.getString("name"));
				list.add(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<BanJi> search(int begin) {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			creatConnection();
			String sql = "SELECT * FROM banji limit " + begin + ", 5;";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				BanJi banJi = new BanJi();
				banJi.setId(rs.getInt("id"));
				banJi.setName(rs.getString("name"));
				list.add(banJi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int searchCount(BanJi conditionBanJi) {
		int count = 0;
		try {
			creatConnection();
			String sql = "SELECT count(id) as n FROM banji where 1=1 ";
			if (!conditionBanJi.getName().equals("")) {
				sql += " and name like + '%" + conditionBanJi.getName() + "%'";
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

	public int add(BanJi banJi) {
		int result = 0;
		String sql = "insert into banji(name) VALUE (?)";
		try {
			creatConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banJi.getName());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public BanJi searchById(int id) {
		BanJi banJi = null;
		try {
			creatConnection();
			String sql = "SELECT * FROM banji where id=" + id + ";";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				banJi = new BanJi();
				banJi.setId(rs.getInt("id"));
				banJi.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return banJi;
	}

	public int update(BanJi banJi) {
		int result = 0;
		try {
			creatConnection();
			String sql = "update banji set name=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banJi.getName());
			preparedStatement.setInt(2, banJi.getId());
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
			String sql = "delete from banji WHERE id =" + id;
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<BanJi> searchByCondition(BanJi conditionBanJi, int begin) {
		 List<BanJi> list = new ArrayList<BanJi>();
		 try {
			 creatConnection();
			 String sql = "select * from banji where 1=1 ";
			 if (!conditionBanJi.getName().equals("")) {
				 sql += " and name like + '%" + conditionBanJi.getName() + "%'";
			 }
			 sql += "  limit " + begin + ", 5;";
			 preparedStatement = connection.prepareStatement(sql);
			 rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				conditionBanJi = new BanJi();
				conditionBanJi.setId(rs.getInt("id"));
				conditionBanJi.setName(rs.getString("name"));
				list.add(conditionBanJi);
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 close();
		 }
		 return list;
	}

	public BanJi showSubject(int id) {
		BanJi banJi = new BanJi();
		List<Subject> subList = new ArrayList<Subject>();
		try {
			creatConnection();
			String sql = "SELECT sub.id as subId ,sub.name AS subName" +
					" FROM banji AS c RIGHT JOIN m_bj_sub AS m ON c.id=m.bj_id " +
					"RIGHT JOIN subject AS sub ON m.sub_id=sub.id WHERE " +
					"c.id =" + id + ";";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("subId"));
				subject.setName(rs.getString("subName"));
				subList.add(subject);
			}
			banJi.setSubList(subList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return banJi;
	}

	public int addSubject(int bjId, int subId) {
		int result = 0;
		try {
			creatConnection();
			String sql = "INSERT into m_bj_sub (bj_id, sub_id) VALUE (?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bjId);
			preparedStatement.setInt(2, subId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int deleteSubject(int bjId, int subId) {
		int result = 0;
		try {
			creatConnection();
			String sql = "delete from m_bj_sub WHERE bj_id =? and sub_id =?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bjId);
			preparedStatement.setInt(2, subId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

}
