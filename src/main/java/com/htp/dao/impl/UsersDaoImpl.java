package com.htp.dao.impl;

import com.htp.entity.Users;
import com.htp.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("UsersDaoImpl")
@Transactional
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Users getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setCreated(resultSet.getTimestamp("created"));
        user.setChanged(resultSet.getTimestamp("changed"));
        user.setDeleted(resultSet.getBoolean("is_deleted"));
        user.seteMail(resultSet.getString("e_mail"));
        user.setPhNumberUser(resultSet.getLong("phone_number_user"));
        return user;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Users entity) {

        String sql = "INSERT INTO m_users " +
                "(id, login, password, created, changed, is_deleted, e_mail, phone_number_user) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getLogin(), entity.getPassword(), entity.getCreated(),
                entity.getChanged(), entity.getDeleted(), entity.geteMail(), entity.getPhNumberUser()});
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM m_users ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Users findById(Long id) {
        String sql = "SELECT * FROM m_users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

   /* @Override
    public Users readById(int id) {
        return null;
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Users> update(Users entity) {
        final String sql = "UPDATE m_users set login = :login, password = :password, created = :created, changed = :changed, is_deleted = :is_deleted, e_mail = :e_mail, phone_number_user = :phone_number_user where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", entity.getLogin());
        params.addValue("password", entity.getPassword());
        params.addValue("created", entity.getCreated());
        params.addValue("changed", entity.getChanged());
        params.addValue("is_deleted", entity.getDeleted());
        params.addValue("e_mail", entity.geteMail());
        params.addValue("phone_number_user", entity.getPhNumberUser());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_users where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    public List<Users> findAllDeletedUsers() {
        String sql = "SELECT * FROM m_users WHERE is_deleted = true";
               return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public List<Users> createdAfter() {
        String sql = "SELECT * FROM m_users WHERE created = 20191212163023.000000";
                //timestamp('27/10/2019 15:51.12.539880', 'dd/mm/yyyy hh24:mi.ss.ff') ";

        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);

    }

}
