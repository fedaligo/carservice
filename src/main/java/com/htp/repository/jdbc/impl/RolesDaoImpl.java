package com.htp.repository.jdbc.impl;

import com.htp.repository.jdbc.RolesDao;
import com.htp.domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository("RolesDaoImpl")
@Transactional
public class RolesDaoImpl implements RolesDao {
    public static final String ID ="id";
    public static final String NAME_OF_ROLE ="name_of_role";
    public static final String USER_ID ="user_id";


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Roles getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Roles roles = new Roles();
        roles.setId(resultSet.getLong(ID));
        roles.setNameOfRole(resultSet.getString(NAME_OF_ROLE));
        roles.setUserId(resultSet.getLong(USER_ID));
        return roles;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Roles entity) {

        String sql = "INSERT INTO m_roles " +
                "(id, name_of_role, user_id) " +
                "VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getNameOfRole(), entity.getUserId()});
    }

    @Override
    public List<Roles> findAll() {
        String sql = "SELECT * FROM m_roles ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Roles findById(Long id) {
        String sql = "SELECT * FROM m_roles WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

   /* @Override
    public Users readById(int id) {
        return null;
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Roles> update(Roles entity) {
        final String sql = "UPDATE m_roles set name_of_role = :name_of_role, user_id = :user_id, " +
                " where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NAME_OF_ROLE, entity.getNameOfRole());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_roles where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        namedParameterJdbcTemplate.update(sql, params);

    }
/*
    @Override
    public List<Tracking> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM tracking_system WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cost", cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }*/

    @Override
    public List<Roles> getRolesByUserId(Long userId) {
        final String getRolesByUserId = "select * from m_roles where name_of_role = ?";
        return jdbcTemplate.query(getRolesByUserId, new Object[]{userId}, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Roles save(Roles entity) {

        final String sql = "INSERT INTO m_roles (name_of_role , user_id)" +
                "VALUES (:name_of_role, :user_id);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NAME_OF_ROLE, entity.getNameOfRole());
        params.addValue(USER_ID, entity.getUserId());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdRoleId);


    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Roles updateOne(Roles entity) {
        final String sql = "UPDATE m_roles set name_of_role = :name_of_role, user_id = :user_id, " +
                " where id = :id";

        //KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NAME_OF_ROLE, entity.getNameOfRole());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(ID, entity.getId());

        namedParameterJdbcTemplate.update(sql, params);
        //long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(entity.getId());
    }
}
