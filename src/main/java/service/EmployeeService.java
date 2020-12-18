package service;

import bl.Util;
import dao.EmployeeDAO;
import entity.Address;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends Util implements EmployeeDAO {
    Connection connection = getConnection();

    @Override
    public void add(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) VALUES(?, ?, ?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthDate()) ;
            preparedStatement.setLong(5, employee.getAddressId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> list = new ArrayList<Employee>();
        Statement statement = null;
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";
        try{
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getLong("ID"));
                employee.setFirstName(rs.getString("COUNTRY"));
                employee.setLastName(rs.getString("COUNTRY"));
                employee.setBirthDate(rs.getDate("STREET"));
                employee.setAddressId(rs.getLong("POST_CODE"));
                list.add(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        }
        return list;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM EMPLOYEE WHERE ID = ?";
        Employee employee = new Employee();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            employee.setId(rs.getLong("ID"));
            employee.setFirstName(rs.getString("FIRST_NAME"));
            employee.setLastName(rs.getString("LAST_NAME"));
            employee.setBirthDate(rs.getDate("BIRTHDAY"));
            employee.setAddressId(rs.getLong("ADDRESS_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return employee;
    }

    @Override
    public void update(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, ADDRESS_ID=?, WHERE ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthDate());
            preparedStatement.setLong(4, employee.getAddressId());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    @Override
    public void remove(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }
}
