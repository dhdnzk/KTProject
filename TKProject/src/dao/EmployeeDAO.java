package dao;

import dao.support.EmployeeSearchMode;
import exception.DeleteFailedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionManager.*;

// TODO : Add comment
class EmployeeDAO {

    // TODO : Add commentint
    List<EmployeeBean> employeeSearchByString(EmployeeSearchMode mode, String search) throws Exception {

        String sql = "SELECT * FROM m_employee JOIN m_section ON m_employee.section_code = m_section.section_code WHERE ";

        switch(mode) {
            case SEARCH_CODE:
                sql += "emp_code";
                break;

            case SEARCH_KANJI:
                sql += "CONCAT(l_name, f_name)";
                break;

            case SEARCH_NAME:
                sql += "CONCAT(l_kana_name, f_kana_name)";
                break;

            case SEARCH_SECTION:
                sql += "section_name";
                break;

            default:
                throw new Exception();
        }

        sql += " LIKE '%" + search + "%'";

        ResultSet resultSet = ConnectionManager.getInstance()
                .getConnectionStatement().executeQuery(sql);

        List<EmployeeBean> employeeBeanArrayList = new ArrayList<EmployeeBean>();

        while(resultSet.next()){

            EmployeeBean employeeBean = new EmployeeBean();

            employeeBean.setEmpCode(resultSet.getString("emp_code"));

            employeeBean.setLName(resultSet.getString("l_name"));

            employeeBean.setFName(resultSet.getString("f_name"));

            employeeBean.setLKana(resultSet.getString("l_kana_name"));

            employeeBean.setFKana(resultSet.getString("f_kana_name"));

            employeeBean.setSex(resultSet.getByte("sex"));

            employeeBean.setBirth(resultSet.getDate("birth_day"));

            employeeBean.setSectionCode(resultSet.getString("section_code"));

            employeeBean.setEmpDate(resultSet.getDate("emp_date"));

            employeeBean.setUpdateDate(resultSet.getTimestamp("update_date"));

            employeeBeanArrayList.add(employeeBean);

        }

        ConnectionManager.invalidate();

        return employeeBeanArrayList;

    }


    // TODO : Add comment
    ArrayList<EmployeeBean> getAllEmployees() throws Exception {

        String sql = "SELECT * FROM m_employee";

        ResultSet resultSet = ConnectionManager.getInstance()
                .getConnectionStatement().executeQuery(sql);

        ArrayList<EmployeeBean> employeeBeanArrayList = new ArrayList<>();

        while(resultSet.next()){

            EmployeeBean employeeBean = new EmployeeBean();

            employeeBean.setEmpCode(resultSet.getString("emp_code"));

            employeeBean.setLName(resultSet.getString("l_name"));

            employeeBean.setFName(resultSet.getString("f_name"));

            employeeBean.setLKana(resultSet.getString("l_kana_name"));

            employeeBean.setFKana(resultSet.getString("f_kana_name"));

            employeeBean.setSex(resultSet.getByte("sex"));

            employeeBean.setBirth(resultSet.getDate("birth_day"));

            employeeBean.setSectionCode(resultSet.getString("section_code"));

            employeeBean.setEmpDate(resultSet.getDate("emp_date"));

            employeeBean.setUpdateDate(resultSet.getTimestamp("update_date"));

            employeeBeanArrayList.add(employeeBean);

        }

        ConnectionManager.invalidate();

        return employeeBeanArrayList;

    }

    // TODO : Add comment
    void addEmployee (EmployeeBean employeeBean) throws Exception {

        String sqlQuery = "INSERT INTO m_employee (l_name, f_name, " +
                "l_kana_name, f_kana_name, sex, birth_day, section_code, " +
                "emp_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = ConnectionManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, employeeBean.getLName());

        preparedStatement.setString(2, employeeBean.getFName());

        preparedStatement.setString(3, employeeBean.getLKana());

        preparedStatement.setString(4, employeeBean.getFKana());

        preparedStatement.setByte(5, employeeBean.getSex());

        preparedStatement.setDate(6, employeeBean.getBirth());

        preparedStatement.setString(7, employeeBean.getSectionCode());

        preparedStatement.setDate(8, employeeBean.getEmpDate());

        preparedStatement.executeUpdate();

        ConnectionManager.invalidate();

    }

    // TODO : Create QUERY sentence to delete the selected record.
    // TODO : Add comment
    // FIXME : what is the meaning of parameter?
    void deleteEmployee(String code) throws DeleteFailedException, Exception {

        String sql = "DELETE FROM m_employee WHERE emp_code = ?";

        Connection con = ConnectionManager.getInstance().getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, code);

        int num = pstmt.executeUpdate();

        if(num != 1) {
            throw new DeleteFailedException();
        }

    }

    /**
     *
     * @return ArrayList<String> :
     * Return a array list which is composed of emp_sys_db.m_section's records.
     * This method is for getting current department list which is saved in
     * database. Via request object instance, "registration.jsp" file references
     * this list to make "<select>"tag.
     *
     * FIXME :
     * @throws Exception : This method just throws all of exceptions.
     * I don't have any idea to handle this exceptions yet.
     * If you have a good idea, please fix it.
     */
    ArrayList<String> getDepartmentListFromMSectionTable() throws Exception {

        String query = "SELECT section_name from emp_sys_db.m_section";

        ResultSet resultSet = ConnectionManager.getInstance()
                .getConnectionStatement().executeQuery(query);

        ArrayList<String> sectionList = new ArrayList<>();

        while(resultSet.next()) {

            sectionList.add(resultSet.getString("section_name"));

        }

        ConnectionManager.invalidate();

        return sectionList;

    }

}
