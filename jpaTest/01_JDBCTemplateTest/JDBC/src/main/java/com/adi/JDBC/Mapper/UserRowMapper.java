package com.adi.JDBC.Mapper;

import com.adi.JDBC.POJO.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));        // Mapping manually
        user.setName(rs.getString("user_name"));  // Mapping manually
        user.setAge(rs.getInt("user_age"));       // Mapping manually
        return user;
    }
}
