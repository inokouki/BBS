package kadai4.dao;

import static kadai4.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kadai4.beans.User;
import kadai4.exception.NoRowsUpdatedRuntimeException;
import kadai4.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String accountOrEmail, String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM test1.user WHERE (account = ? OR email = ?) AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, accountOrEmail);
			ps.setString(2, accountOrEmail);
			ps.setString(3, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				int available = 1;
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				User user = new User();
				user.setId(id);
				user.setLogin_Id(login_id);
				user.setName(name);
				user.setPassword(password);
				user.setBranch_Id(branch_id);
				user.setDepartment_Id(department_id);
				user.setAvailable(available);
				user.setCreated(created);
				user.setModified(modified);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO kadai4.users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");

			sql.append(", available");
			sql.append(", created");
			sql.append(", modified");
			sql.append(") VALUES (");
			sql.append("?");  // login_id
			sql.append(", ?");  // password
			sql.append(", ?");  // name
			sql.append(", ?");  // branch_id
			sql.append(", ?");  // department_id
			sql.append(", 1");  // available
			sql.append(", CURRENT_TIMESTAMP"); // created
			sql.append(", CURRENT_TIMESTAMP"); // modified
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_Id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_Id());
			ps.setInt(5, user.getDepartment_Id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE test1.user SET");
			sql.append(" account = ?");
			sql.append(", name = ?");
			sql.append(", email = ?");
			sql.append(", password = ?");
			sql.append(", description = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE");
			sql.append(" id = ?");
			sql.append(" AND");
			sql.append(" update_date = ?");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_Id());
			ps.setString(2, user.getName());
			ps.setString(4, user.getPassword());
			ps.setInt(6, user.getId());
			ps.setTimestamp(7, new Timestamp(user.getCreated().getTime()));

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}