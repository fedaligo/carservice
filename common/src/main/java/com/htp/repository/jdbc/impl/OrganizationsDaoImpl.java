package com.htp.repository.jdbc.impl;

import com.htp.domain.Organizations;
import com.htp.repository.jdbc.OrganizationsDao;
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
import java.util.List;
import java.util.Objects;

@Repository("OrganizationsDaoImpl")
@RequiredArgsConstructor
@Transactional
public class OrganizationsDaoImpl implements OrganizationsDao {
    public static final String ID ="id";
    public static final String LOGIN ="login";
    public static final String PASSWORD ="password";
    public static final String ROLE ="role";
    public static final String WEB_SITE ="web_site";
    public static final String PHONE_NUMBER ="phone_number";
    public static final String LOCATION ="location";
    public static final String WORKING_TIME ="working_time";
    public static final String SPECIALIZE ="specialize";
    public static final String E_MAIL ="e_mail";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper parsing resultset
    private Organizations getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Organizations organizations = new Organizations();
        organizations.setId(resultSet.getLong(ID));
        organizations.setLogin(resultSet.getString(LOGIN));
        organizations.setPassword(resultSet.getString(PASSWORD));
        organizations.setRole(resultSet.getString(ROLE));
        organizations.setWebSite(resultSet.getString(WEB_SITE));
        organizations.setPhoneNumber(resultSet.getLong(PHONE_NUMBER));
        organizations.setLocation(resultSet.getString(LOCATION));
        organizations.setWorkingTime(resultSet.getString(WORKING_TIME));
        organizations.setSpecialize(resultSet.getString(SPECIALIZE));
        organizations.setEMail(resultSet.getString(E_MAIL));
        return organizations;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Organizations entity) {

        String sql = "INSERT INTO m_organization " +
                "(id, login, password, role, web_site, phone_number, location, working_time, specialize, e_mail) " +
                "VALUES (:id, :login, :password, :role, :web_site, :phone_number, :location, :working_time, :specialize, :e_mail)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getLogin(), entity.getPassword(), entity.getRole(), entity.getWebSite(), entity.getPhoneNumber(),
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
        params.addValue(ID, id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Organizations> update(Organizations entity) {
        final String sql = "UPDATE m_organization set login = :login, password = : password, role = :role, web_site = :web_site, " +
                "phone_number = :phone_number, location = :location, working_time = :working_time, specialize = :specialize, " +
                "e_mail = :e_mail  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(ROLE, entity.getRole());
        params.addValue(WEB_SITE, entity.getWebSite());
        params.addValue(PHONE_NUMBER, entity.getPhoneNumber());
        params.addValue(LOCATION, entity.getLocation());
        params.addValue(WORKING_TIME, entity.getWorkingTime());
        params.addValue(SPECIALIZE, entity.getSpecialize());
        params.addValue(E_MAIL, entity.getEMail());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_organization where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Organizations save(Organizations entity) {
        final String sql = "INSERT INTO m_organization (login, password, role, web_site, phone_number, location, working_time, specialize, e_mail) " +
                "VALUES (:login, :password, :role, :web_site, :phone_number, :location, :working_time, :specialize, :e_mail)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(ROLE, entity.getRole());
        params.addValue(PHONE_NUMBER, entity.getPhoneNumber());
        params.addValue(LOCATION, entity.getLocation());
        params.addValue(WORKING_TIME, entity.getWorkingTime());
        params.addValue(SPECIALIZE, entity.getSpecialize());
        params.addValue(E_MAIL, entity.getEMail());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});
        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Organizations updateOne(Organizations entity) {
        final String sql = "UPDATE m_organization set login = :login, password = :password, role =: role,  web_site = :web_site, " +
                "phone_number = :phone_number, location = :location, working_time = :working_time, specialize = :specialize, " +
                "e_mail = :e_mail  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(LOGIN, entity.getLogin());
        params.addValue(PASSWORD, entity.getPassword());
        params.addValue(ROLE, entity.getRole());
        params.addValue(WEB_SITE, entity.getWebSite());
        params.addValue(PHONE_NUMBER, entity.getPhoneNumber());
        params.addValue(LOCATION, entity.getLocation());
        params.addValue(WORKING_TIME, entity.getWorkingTime());
        params.addValue(SPECIALIZE, entity.getSpecialize());
        params.addValue(E_MAIL, entity.getEMail());
        params.addValue(ID, entity.getId());

        namedParameterJdbcTemplate.update(sql, params);
        return findById(entity.getId());
    }

}
