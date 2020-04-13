package com.htp.repository.jdbc.impl;

import com.htp.domain.Tasks;
import com.htp.repository.jdbc.TasksDao;
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

@RequiredArgsConstructor
@Repository("TasksDaoImpl")
@Transactional
public class TasksDaoImpl implements TasksDao {
    public static final String ID ="id";
    public static final String SERVICE_WORK_NAME ="service_work_name";
    public static final String NECESSITY_OF_EVACUATION ="necessity_of_evacuation";
    public static final String WHEEL_BRAKE ="wheel_brake";
    public static final String ID_CAR ="id_car";
    public static final String CREATED ="created";
    public static final String DESCRIPTION ="description";
    public static final String LATITUDE ="latitude";
    public static final String LONGITUDE ="longitude";
    public static final String LOCAL_DESCRIPTION ="local_description";

    //@Autowired
    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper parsing resultset
    private Tasks getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Tasks tasks = new Tasks();
        tasks.setId(resultSet.getLong(ID));
        tasks.setServiceWorkName(resultSet.getString(SERVICE_WORK_NAME));
        tasks.setNecessityOfEvacuation(resultSet.getBoolean(NECESSITY_OF_EVACUATION));
        tasks.setWheelBrake(resultSet.getBoolean(WHEEL_BRAKE));
        tasks.setIdCar(resultSet.getLong(ID_CAR));
        tasks.setCreated(resultSet.getDate(CREATED));
        tasks.setDescription(resultSet.getString(DESCRIPTION));
        tasks.setLatitude(resultSet.getDouble(LATITUDE));
        tasks.setLongitude(resultSet.getDouble(LONGITUDE));
        tasks.setLocalDescription(resultSet.getString(LOCAL_DESCRIPTION));
        return tasks;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Tasks entity) {

        String sql = "INSERT INTO m_tasks " +
                "(id, service_work_name, necessity_of_evacuation, wheel_brake, id_car, created, description, latitude, " +
                "longitude, local_description) " +
                "VALUES (:id, :service_work_name, :necessity_of_evacuation, :wheel_brake, :id_car, :created, :description, :latitude, \" +\n" +
                "                \":longitude, :local_description)";

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
        params.addValue(ID, id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Tasks> update(Tasks entity) {
        final String sql = "UPDATE m_tasks set service_work_name = :service_work_name, necessity_of_evacuation = :necessity_of_evacuation, " +
                "wheel_brake = :wheel_brake, id_car = :id_car, created = :created , description = :description, latitude = :latitude" +
                ", longitude = :longitude, local_description = :local_description where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SERVICE_WORK_NAME, entity.getServiceWorkName());
        params.addValue(NECESSITY_OF_EVACUATION, entity.getNecessityOfEvacuation());
        params.addValue(WHEEL_BRAKE, entity.getWheelBrake());
        params.addValue(ID_CAR, entity.getIdCar());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(DESCRIPTION, entity.getDescription());
        params.addValue(LATITUDE, entity.getLatitude());
        params.addValue(LONGITUDE, entity.getLongitude());
        params.addValue(LOCAL_DESCRIPTION, entity.getLocalDescription());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_tasks where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Tasks save(Tasks entity) {
        final String sql = "INSERT INTO m_tasks " +
                "(service_work_name, necessity_of_evacuation, wheel_brake, id_car, created, description, latitude, " +
                "longitude, local_description) " +
                "VALUES (:service_work_name, :necessity_of_evacuation, :wheel_brake, :id_car, :created, :description, :latitude, " +
                ":longitude, :local_description)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SERVICE_WORK_NAME, entity.getServiceWorkName());
        params.addValue(NECESSITY_OF_EVACUATION, entity.getNecessityOfEvacuation());
        params.addValue(WHEEL_BRAKE, entity.getWheelBrake());
        params.addValue(ID_CAR, entity.getIdCar());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(DESCRIPTION, entity.getDescription());
        params.addValue(LATITUDE, entity.getLatitude());
        params.addValue(LONGITUDE, entity.getLongitude());
        params.addValue(LOCAL_DESCRIPTION, entity.getLocalDescription());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});

        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Tasks updateOne(Tasks entity) {
        final String sql = "UPDATE m_tasks set service_work_name = :service_work_name, necessity_of_evacuation = :necessity_of_evacuation, " +
                "wheel_brake = :wheel_brake, id_car = :id_car, created = :created , description = :description, latitude = :latitude" +
                ", longitude = :longitude, local_description = :local_description where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(SERVICE_WORK_NAME, entity.getServiceWorkName());
        params.addValue(NECESSITY_OF_EVACUATION, entity.getNecessityOfEvacuation());
        params.addValue(WHEEL_BRAKE, entity.getWheelBrake());
        params.addValue(ID_CAR, entity.getIdCar());
        params.addValue(CREATED, entity.getCreated());
        params.addValue(DESCRIPTION, entity.getDescription());
        params.addValue(LATITUDE, entity.getLatitude());
        params.addValue(LONGITUDE, entity.getLongitude());
        params.addValue(LOCAL_DESCRIPTION, entity.getLocalDescription());
        params.addValue(ID, entity.getId());

        namedParameterJdbcTemplate.update(sql, params);
        return findById(entity.getId());
    }
}
