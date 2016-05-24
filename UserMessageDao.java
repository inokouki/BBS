package kadai4.dao;

import static kadai4.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kadai4.beans.UserMessage;
import kadai4.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.contributions ");
			sql.append("ORDER BY created DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				String user_id = rs.getString("user_id");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				UserMessage message = new UserMessage();

				message.setId(id);
				message.setTitle(title);
				message.setText(text);
				message.setCategory(category);
				message.setUserId(user_id);
				message.setCreated(created);
				message.setModified(modified);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}