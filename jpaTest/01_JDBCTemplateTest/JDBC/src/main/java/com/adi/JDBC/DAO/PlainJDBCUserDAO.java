package com.adi.JDBC.DAO;

import com.adi.JDBC.Config.DatabaseConnection;
import com.adi.JDBC.POJO.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("PlainJDBC")

public class PlainJDBCUserDAO implements UserDAO {

    public void createUserTable(){
        try{
            Connection connection = new DatabaseConnection().getConnection();
            Statement statementQuery = connection.createStatement();
            String sql = "CREATE TABLE users(user_id int auto_increment primary key , " +
                    "user_name varchar(100) , user_age int)";
            statementQuery.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String userName, int userAge){
        try{
            Connection connection = new DatabaseConnection().getConnection();
            String sql = "INSERT INTO USERS(user_name,user_age) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setInt(2,userAge);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> readUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet output = preparedStatement.executeQuery()) {

            while (output.next()) {
                users.add(new User(
                        output.getInt("user_id"),
                        output.getString("user_name"),
                        output.getInt("user_age")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<String> readUserNames() {
        return List.of();
    }

    @Override
    public User readUserById(int userId) {
        return null;
    }

    @Override
    public int getCountOfUsers() {
        return 0;
    }

}
