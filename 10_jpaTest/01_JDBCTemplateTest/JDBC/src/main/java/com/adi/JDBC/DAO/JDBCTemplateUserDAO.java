package com.adi.JDBC.DAO;

import com.adi.JDBC.Mapper.UserRowMapper;
import com.adi.JDBC.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Qualifier("JDBCTemplate")
public class JDBCTemplateUserDAO implements UserDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createUserTable(){
        jdbcTemplate.execute("CREATE TABLE users(user_id int auto_increment primary key , " +
                "user_name varchar(100) , user_age int)");
    }

    public void createUser(String userName,int userAge){
        // jdbcTemplate.update( "INSERT INTO USERS(user_name,user_age) VALUES (?,?)",userName,userAge);
        jdbcTemplate.update( "INSERT INTO USERS(user_name,user_age) VALUES (?,?)",(PreparedStatement pss)->{
            pss.setString(1,userName);
            pss.setInt(2,userAge);
        });
    }

    public List<User> readUsers(){
        return jdbcTemplate.query("SELECT * FROM users",(rs,rowNum) -> {
            return new User(rs.getInt("user_id"),rs.getString("user_name"),
                    rs.getInt("user_age"));
        });
    }

    public List<String> readUserNames(){
        return jdbcTemplate.queryForList("select user_name from users",String.class);
    }

    public User readUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        /*
        BeanPropertyRowMapper uses simple naming rules:
        it looks for exact matches between column names and field names (ignores case sensitivity
        , but not extra prefixes like user_).
So       it fails to find the matching field if column names have prefixes or different styles.
         */
        
//        String sql = "SELECT user_id AS id, user_name AS name, user_age AS age FROM users WHERE user_id = ?";
//
//        return jdbcTemplate.queryForObject(
//                sql,
//                new BeanPropertyRowMapper<>(User.class),
//                userId
//        );

        return jdbcTemplate.queryForObject(
                sql,
                new UserRowMapper(),
                userId
        );


    }

    public int getCountOfUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
