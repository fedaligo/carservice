package com.htp.dao.impl;

import com.htp.dao.RolesDao;
import com.htp.entity.Roles;
import com.htp.entity.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("RolesDaoImpl")
@Transactional
public class RolesDaoImpl implements RolesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Roles getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Roles roles = new Roles();
        roles.setId(resultSet.getLong("id"));
        roles.setNameOfRole(resultSet.getString("name_of_role"));
        roles.setUserId(resultSet.getLong("user_id"));
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
        params.addValue("id", id);
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
        params.addValue("name_of_role", entity.getNameOfRole());
        params.addValue("user_id", entity.getUserId());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_roles where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
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

}
