package com.htp.dao.jdbc.impl;

import com.htp.dao.jdbc.OrganizationsDao;
import com.htp.entity.Organizations;
import com.htp.entity.Roles;
import com.htp.entity.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Objects;

@Repository("OrganizationsDaoImpl")
@Transactional
public class OrganizationsDaoImpl implements OrganizationsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Organizations getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Organizations organizations = new Organizations();
        organizations.setId(resultSet.getLong("id"));
        organizations.setName(resultSet.getString("name"));
        organizations.setWebSite(resultSet.getString("web_site"));
        organizations.setPhoneNumber(resultSet.getLong("phone_number"));
        organizations.setLocation(resultSet.getString("location"));
        organizations.setWorkingTime(resultSet.getString("working_time"));
        organizations.setSpecialize(resultSet.getString("specialize"));
        organizations.setEMail(resultSet.getString("e_mail"));
        return organizations;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Organizations entity) {

        String sql = "INSERT INTO m_organization " +
                "(id, name, web_site, phone_number, location, working_time, specialize, e_mail) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getName(), entity.getWebSite(), entity.getPhoneNumber(),
                entity.getLocation(), entity.getWorkingTime(), entity.getSpecialize(), entity.getEMail()});
    }

    @Override
    public List<Organizations> findAll() {
        String sql = "SELECT * FROM m_organization ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Organizations findById(Long id) {
        String sql = "SELECT * FROM m_organization WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

   /* @Override
    public Users readById(int id) {
        return null;
    }*/
//id, name, web_site, phone_number, location, working_time, specialize, e_mail
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Organizations> update(Organizations entity) {
        final String sql = "UPDATE m_organization set name = :name, web_site = :web_site, " +
                "phone_number = :phone_number, location = :location, working_time = :working_time, specialize = :specialize, " +
                "e_mail = :e_mail  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", entity.getName());
        params.addValue("web_site", entity.getWebSite());
        params.addValue("phone_number", entity.getPhoneNumber());
        params.addValue("location", entity.getLocation());
        params.addValue("working_time", entity.getWorkingTime());
        params.addValue("specialize", entity.getSpecialize());
        params.addValue("e_mail", entity.getEMail());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_organization where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Organizations save(Organizations entity) {
        final String sql = "INSERT INTO m_organization " +
                "(id, name, web_site, phone_number, location, working_time, specialize, e_mail) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", entity.getName());
        params.addValue("web_site", entity.getWebSite());
        params.addValue("phone_number", entity.getPhoneNumber());
        params.addValue("location", entity.getLocation());
        params.addValue("working_time", entity.getWorkingTime());
        params.addValue("specialize", entity.getSpecialize());
        params.addValue("e_mail", entity.getEMail());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Organizations updateOne(Organizations entity) {
        final String sql = "UPDATE m_organization set name = :name, web_site = :web_site, " +
                "phone_number = :phone_number, location = :location, working_time = :working_time, specialize = :specialize, " +
                "e_mail = :e_mail  where id = :id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", entity.getName());
        params.addValue("web_site", entity.getWebSite());
        params.addValue("phone_number", entity.getPhoneNumber());
        params.addValue("location", entity.getLocation());
        params.addValue("working_time", entity.getWorkingTime());
        params.addValue("specialize", entity.getSpecialize());
        params.addValue("e_mail", entity.getEMail());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdId);
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
