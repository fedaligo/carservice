package com.htp.dao.jdbc.impl;

import com.htp.dao.jdbc.TasksDao;
import com.htp.entity.Tasks;
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

@Repository("TasksDaoImpl")
@Transactional
public class TasksDaoImpl implements TasksDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Tasks getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Tasks tasks = new Tasks();
        tasks.setId(resultSet.getLong("id"));
        tasks.setServiceWorkName(resultSet.getString("service_work_name"));
        tasks.setNecessityOfEvacuation(resultSet.getBoolean("necessity_of_evacuation"));
        tasks.setWheelBrake(resultSet.getBoolean("wheel_brake"));
        tasks.setIdCar(resultSet.getLong("id_car"));
        tasks.setCreated(resultSet.getDate("created"));
        tasks.setDescription(resultSet.getString("description"));
        tasks.setLatitude(resultSet.getDouble("latitude"));
        tasks.setLongitude(resultSet.getDouble("longitude"));
        tasks.setLocalDescription(resultSet.getString("local_description"));
        return tasks;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Tasks entity) {

        String sql = "INSERT INTO m_tasks " +
                "(id, service_work_name, necessity_of_evacuation, wheel_brake, id_car, created, description, latitude, " +
                "longitude, local_description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getServiceWorkName(), entity.getNecessityOfEvacuation(),
                entity.getWheelBrake(), entity.getIdCar(),entity.getCreated(),entity.getDescription(),entity.getLatitude(),
                entity.getLongitude(), entity.getLocalDescription()});
    }

    @Override
    public List<Tasks> findAll() {
        String sql = "SELECT * FROM m_tasks ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Tasks findById(Long id) {
        String sql = "SELECT * FROM m_tasks WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

   /* @Override
    public Users readById(int id) {
        return null;
    }*/
//id, service_work_name, necessity_of_evacuation, wheel_brake, id_car, created, description, latitude, " +
//                "longitude, local_description
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Tasks> update(Tasks entity) {
        final String sql = "UPDATE m_tasks_id_seq set service_work_name = :service_work_name, necessity_of_evacuation = :necessity_of_evacuation, " +
                "wheel_brake = :wheel_brake, id_car = :id_car, created = :created , description = :description, latitude = :latitude" +
                ", longitude = :longitude, local_description = :local_description where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("service_work_name", entity.getServiceWorkName());
        params.addValue("necessity_of_evacuation", entity.getNecessityOfEvacuation());
        params.addValue("wheel_brake", entity.getWheelBrake());
        params.addValue("id_car", entity.getIdCar());
        params.addValue("created", entity.getCreated());
        params.addValue("description", entity.getDescription());
        params.addValue("latitude", entity.getLatitude());
        params.addValue("longitude", entity.getLongitude());
        params.addValue("local_description", entity.getLocalDescription());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_tasks where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    /*@Override
    public List<Tracking> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM tracking_system WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cost", cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }*/

}
