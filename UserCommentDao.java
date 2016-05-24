package kadai4.dao;

import static kadai4.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kadai4.beans.UserComment;
import kadai4.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getUserComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.comments ");
			sql.append("ORDER BY created DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs) throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String text = rs.getString("text");
				String contribution_id = rs.getString("contribution_id");
				String user_id = rs.getString("user_id");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				UserComment comment = new UserComment();

				comment.setId(id);
				comment.setText(text);
				comment.setContributionId(contribution_id);
				comment.setUserId(user_id);
				comment.setCreated(created);
				comment.setModified(modified);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}