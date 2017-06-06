package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ConnectionManager.*;

/**
 *
 * UserDAO
 * This class is about to mysql table emp_sys_db.m_user.
 * If you need to access to m_user table, check here first.
 *
 * @author Nekotoken
 * @version 1.0.0
 */
enum UserDAO {

    USER_DAO;

    /**
     *
     * tryLogin
     * Connect to mysql database and check emp_sys_db.m_user table in database
     * If success to login, nothing happen.
     * If failed to login, throws exception.
     *
     * @param id : get user id from DAOManager, which is from user input on web.
     * @param password : get user id from DAOManager, which is from user input on web.
     * @throws Exception :
     * If failed to login, method throws exception.
     * So if there was no exception, it means succeed to login.
     */
    void tryLogin(String id, String password) throws Exception {

        Connection connection = ConnectionManager.getInstance().getConnection();

        String sqlQuery = "SELECT * FROM m_user WHERE user_id=? AND password=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, id);

        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(!resultSet.next()) {

            ConnectionManager.invalidate();

            throw new Exception();

        }

    }

}
