package com.htp.repository.jdbc.impl;

import com.htp.domain.Users;
import com.htp.repository.jdbc.UsersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class UsersDaoImpl implements UsersDao {
    public static final String ID ="id";
    public static final String LOGIN ="login";
    public static final String PASSWORD ="password";
    public static final String CREATED ="created";
    public static final String CHANGED ="changed";
    public static final String IS_DELETED ="is_deleted";
    public static final String PHONE_NUMBER_USER ="phone_number_user";
    public static final String E_MAIL ="e_mail";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper parsing resultset
    private Users getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getLong(ID));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setCreated(resultSet.getTimestamp(CREATED));
        user.setChanged(resultSet.getTimestamp(CHANGED));
        user.setIsDeleted(resultSet.getBoolean(IS_DELETED));
        user.setEMail(resultSet.getString(E_MAIL));
        user.setPhNumberUser(resultSet.getLong(PHONE_NUMBER_USER));
        return user;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Users entity) {

        String sql = "INSERT INTO m_users " +
                "(id, login, password, created, changed, is_deleted, e_mail, phone_number_user) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getLogin(), entity.getPassword(), entity.getCreated(),
                entity.getChanged(), entity.getIsDeleted(), entity.getEMail(), entity.getPhNumberUser()});
    }

    @Override
    public List<Users> findAll() {
        final String sql = "SELECT * FROM m_users ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Users findById(Long id) {
        final String sql = "SELECT * FROM m_users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Users> update(Users entity) {
        final String sql = "UPDATE m_users set login = :login, password = :password, created = :created, changed = :changed, is_deleted = :is_deleted, e_mail = :e_mail, phone_number_user = :phone_number_user where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(CHANGED, entity.getChanged());
        params.addValue(IS_DELETED, entity.getIsDeleted());
        params.addValue(E_MAIL, entity.getEMail());
        params.addValue(PHONE_NUMBER_USER, entity.getPhNumberUser());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_users where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Long> batchUpdate(List<Users> users) {
        return Collections.emptyList();
    }

    @Override
    public Users findByLogin(String login) {
        final String findById = "select * from m_users where login = :login";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, login);
        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Users save(Users entity) {
        final String sql = "INSERT INTO m_users (login , password , created, changed , is_deleted , e_mail , phone_number_user)" +
                "VALUES (:login, :password, :created, :changed, :is_deleted, :e_mail,:phone_number_user);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(CHANGED, entity.getChanged());
        params.addValue(IS_DELETED, entity.getIsDeleted());
        params.addValue(E_MAIL, entity.getEMail());
        params.addValue(PHONE_NUMBER_USER, entity.getPhNumberUser());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});
        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Users updateOne(Users entity) {
        final String sql = "UPDATE m_users set login = :login, password = :password, created = :created, changed = :changed, " +
                "is_deleted = :is_deleted, e_mail = :e_mail, phone_number_user = :phone_number_user where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(CHANGED, entity.getChanged());
        params.addValue(IS_DELETED, entity.getIsDeleted());
        params.addValue(E_MAIL, entity.getEMail());
        params.addValue(PHONE_NUMBER_USER, entity.getPhNumberUser());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);

        return findById(entity.getId());
    }

    @Override
    public List<Users> search(String query, Long limit, Long offset) {
        final String searchQuery = "select * from user where lower(user_name) LIKE lower(:query) or " +
                "lower(user_surname) LIKE lower(:query) limit :lim offset :off";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getEmployeeRowMapper);
    }

}




