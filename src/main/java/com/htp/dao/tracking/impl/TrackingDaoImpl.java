package com.htp.dao.tracking.impl;

import com.htp.dao.tracking.TrackingDao;
import com.htp.entity.Tracking;
import com.htp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("TrackingDaoImpl")
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
        tracking.setId_task(resultSet.getLong("id_task"));
        tracking.setId_organaizer(resultSet.getLong("id_organizer"));
        tracking.setStatus(resultSet.getString("status"));
        tracking.setConfirm_date(resultSet.getDate("confirm_date"));
        tracking.setCost(resultSet.getLong("cost"));
        return tracking;
    }


    @Override
    public void create(Tracking entity) {

        String sql = "INSERT INTO tracking_system " +
                "(id, id_task, id_organizer, status, confirm_date, cost) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getId_task(), entity.getId_organaizer(), entity.getStatus(),
                entity.getConfirm_date(), entity.getCost()});
    }

    @Override
    public List<Tracking> readAll() {
        String sql = "SELECT * FROM tracking_system ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Tracking readById(Long id) {
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
    public Tracking updateById(Tracking entity) {
        final String sql = "UPDATE tracking_system set id_task = :id_task, id_organizer = :id_organizer, " +
                "status = :status, confirm_date = :confirm_date, cost = :cost  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_task", entity.getId_task());
        params.addValue("id_organizer", entity.getId_organaizer());
        params.addValue("status", entity.getStatus());
        params.addValue("confirm_date", entity.getConfirm_date());
        params.addValue("cost", entity.getCost());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return readById(entity.getId());
    }

    @Override
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

}
