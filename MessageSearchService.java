package kadai4.service;

import static kadai4.utils.CloseableUtil.*;
import static kadai4.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import kadai4.beans.SearchMessage;
import kadai4.dao.UserSearchDao;

public class MessageSearchService {

	private static final int LIMIT_NUM = 1000;

	public List<SearchMessage> getMessage(String searchCategory, String searchTimeBefore, String searchTimeAfter) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserSearchDao searchDao = new UserSearchDao();
			List<SearchMessage> ret = searchDao.getUserMessages(connection, LIMIT_NUM,
					searchCategory, searchTimeBefore, searchTimeAfter);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}