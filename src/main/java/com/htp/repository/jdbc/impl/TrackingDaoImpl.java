package com.htp.repository.jdbc.impl;

import com.htp.repository.jdbc.TrackingDao;
import com.htp.domain.Tracking;
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
    public static final String ID ="id";
    public static final String ID_TASK ="id_task";
    public static final String ID_ORGANAIZER ="id_organaizer";
    public static final String STATUS ="status";
    public static final String CONFIRM_DATE ="confirm_date";
    public static final String COST ="cost";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Tracking getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Tracking tracking = new Tracking();
        tracking.setId(resultSet.getLong(ID));
        tracking.setIdTask(resultSet.getLong(ID_TASK));
        tracking.setIdOrganaizer(resultSet.getLong(ID_ORGANAIZER));
        tracking.setStatus(resultSet.getString(STATUS));
        tracking.setConfirmDate(resultSet.getDate(CONFIRM_DATE));
        tracking.setCost(resultSet.getLong(COST));
        return tracking;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Tracking entity) {

        String sql = "INSERT INTO tracking_system " +
                "(id, id_task, id_organaizer, status, confirm_date, cost) " +
                "VALUES (:id, :id_task, :id_organaizer, :status, :confirm_date, :cost)";

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
        params.addValue(ID, id);
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
        params.addValue(ID_TASK, entity.getIdTask());
        params.addValue(ID_ORGANAIZER, entity.getIdOrganaizer());
        params.addValue(STATUS, entity.getStatus());
        params.addValue(CONFIRM_DATE, entity.getConfirmDate());
        params.addValue(COST, entity.getCost());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from tracking_system where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    public List<Tracking> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM tracking_system WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(COST, cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Tracking save(Tracking entity) {
        final String sql = "INSERT INTO tracking_system " +
                "(id_task, id_organaizer, status, confirm_date, cost) " +
                "VALUES (:id_task, :id_organaizer, :status, :confirm_date, :cost)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID_TASK, entity.getIdTask());
        params.addValue(ID_ORGANAIZER, entity.getIdOrganaizer());
        params.addValue(STATUS, entity.getStatus());
        params.addValue(CONFIRM_DATE, entity.getConfirmDate());
        params.addValue(COST, entity.getCost());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});

        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Tracking updateOne(Tracking entity) {
        final String sql = "UPDATE tracking_system set id_task = :id_task, id_organaizer = :id_organaizer, " +
                "status = :status, confirm_date = :confirm_date, cost = :cost  where id = :id";

        //KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID_TASK, entity.getIdTask());
        params.addValue(ID_ORGANAIZER, entity.getIdOrganaizer());
        params.addValue(STATUS, entity.getStatus());
        params.addValue(CONFIRM_DATE, entity.getConfirmDate());
        params.addValue(COST, entity.getCost());
        params.addValue(ID, entity.getId());

        namedParameterJdbcTemplate.update(sql, params);
        //long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(entity.getId());
    }


}
