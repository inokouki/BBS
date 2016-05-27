package kadai4.service;

import static kadai4.utils.CloseableUtil.*;
import static kadai4.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import kadai4.beans.User;
import kadai4.dao.GetUserDao;

public class HomeService {

	private static final int LIMIT_NUM = 1000;

	public List<User> getUser() {
		Connection connection = null;
		try {
			connection = getConnection();

			GetUserDao getUserDao = new GetUserDao();
			List<User> ret = getUserDao.getUsers(connection, LIMIT_NUM);

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