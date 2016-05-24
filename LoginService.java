package kadai4.service;

import static kadai4.utils.CloseableUtil.*;
import static kadai4.utils.DBUtil.*;

import java.sql.Connection;

import kadai4.beans.User;
import kadai4.dao.UserDao;
import kadai4.utils.CipherUtil;

public class LoginService {

	public User login(String loginid, String password) {

		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);



			User user = userDao.getUser(connection, loginid, encPassword);

			commit(connection);
			return user;
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