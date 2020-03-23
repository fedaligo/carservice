package com.htp.dao.jdbc.impl;

import com.htp.dao.jdbc.TrackingDao;
import com.htp.entity.Tracking;
import com.htp.entity.Users;
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

@Repository("TrackingDaoImpl")
@Transactional
public class TrackingDaoImpl implements TrackingDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Tracking getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Tracking tracking = new Tracking();
        tracking.setId(resultSet.getLong("id"));
        tracking.setIdTask(resultSet.getLong("id_task"));
        tracking.setIdOrganaizer(resultSet.getLong("id_organaizer"));
        tracking.setStatus(resultSet.getString("status"));
        tracking.setConfirmDate(resultSet.getDate("confirm_date"));
        tracking.setCost(resultSet.getLong("cost"));
        return tracking;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Tracking entity) {

        String sql = "INSERT INTO tracking_system " +
                "(id, id_task, id_organizer, status, confirm_date, cost) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getIdTask(), entity.getIdOrganaizer(), entity.getStatus(),
                entity.getConfirmDate(), entity.getCost()});
    }

    @Override
    public List<Tracking> findAll() {
        String sql = "SELECT * FROM tracking_system ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Tracking findById(Long id) {
        String sql = "SELECT * FROM tracking_system WHERE id = :id";
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
    public List<Tracking> update(Tracking entity) {
        final String sql = "UPDATE tracking_system set id_task = :id_task, id_organizer = :id_organizer, " +
                "status = :status, confirm_date = :confirm_date, cost = :cost  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_task", entity.getIdTask());
        params.addValue("id_organizer", entity.getIdOrganaizer());
        params.addValue("status", entity.getStatus());
        params.addValue("confirm_date", entity.getConfirmDate());
        params.addValue("cost", entity.getCost());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from tracking_system where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    public List<Tracking> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM tracking_system WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cost", cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Tracking save(Tracking entity) {
        final String sql = "INSERT INTO tracking_system " +
                "(id, id_task, id_organizer, status, confirm_date, cost) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_task", entity.getIdTask());
        params.addValue("id_organizer", entity.getIdOrganaizer());
        params.addValue("status", entity.getStatus());
        params.addValue("confirm_date", entity.getConfirmDate());
        params.addValue("cost", entity.getCost());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Tracking updateOne(Tracking entity) {
        final String sql = "UPDATE tracking_system set id_task = :id_task, id_organizer = :id_organizer, " +
                "status = :status, confirm_date = :confirm_date, cost = :cost  where id = :id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_task", entity.getIdTask());
        params.addValue("id_organizer", entity.getIdOrganaizer());
        params.addValue("status", entity.getStatus());
        params.addValue("confirm_date", entity.getConfirmDate());
        params.addValue("cost", entity.getCost());

        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdId);
    }


}
