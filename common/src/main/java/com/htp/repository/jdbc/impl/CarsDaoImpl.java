package com.htp.repository.jdbc.impl;

import com.htp.domain.Cars;
import com.htp.repository.jdbc.CarsDao;
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

@Repository//("CarsDaoImpl")
@RequiredArgsConstructor
@Transactional
public class CarsDaoImpl implements CarsDao {
    public static final String ID ="id";
    public static final String CAR_BRAND ="car_brand";
    public static final String BRAND_MODEL ="brand_model";
    public static final String TYPE_OF_TRANSMISSION ="type_of_transmission";
    public static final String TYPE_OF_FUEL ="type_of_fuel";
    public static final String VIN_NUMBER ="vin_number";
    public static final String USER_ID ="user_id";
    public static final String CAR_WEIGHT ="car_weight";

    //@Autowired
    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper parsing resultset
    private Cars getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Cars cars = new Cars();
        cars.setId(resultSet.getLong(ID));
        cars.setCarBrand(resultSet.getString(CAR_BRAND));
        cars.setBrandModel(resultSet.getString(BRAND_MODEL));
        cars.setTypeOfTransmission(resultSet.getString(TYPE_OF_TRANSMISSION));
        cars.setTypeOfFuel(resultSet.getString(TYPE_OF_FUEL));
        cars.setVinNumber(resultSet.getString(VIN_NUMBER));
        cars.setUserId(resultSet.getLong(USER_ID));
        cars.setCarWeight(resultSet.getLong(CAR_WEIGHT));
        return cars;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Cars entity) {

        String sql = "INSERT INTO m_car " +
                "(id, car_brand, type_of_transmission, type_of_fuel, vin_number, user_id, car_weight) " +
                "VALUES (:id, :car_brand, :type_of_transmission, :type_of_fuel, :vin_number, :user_id, :car_weight)";

        jdbcTemplate.update(sql, new Object[] { entity.getId(), entity.getCarBrand(), entity.getTypeOfTransmission(), entity.getTypeOfFuel(),
                entity.getVinNumber(), entity.getUserId(), entity.getCarWeight()});
    }

    @Override
    public List<Cars> findAll() {
        String sql = "SELECT * FROM m_car ORDER BY id";
        return namedParameterJdbcTemplate.query(sql, this::getEmployeeRowMapper);
    }

    @Override
    public Cars findById(Long id) {
        String sql = "SELECT * FROM m_car WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Cars> update(Cars entity) {
        final String sql = "UPDATE m_car set car_brand = :car_brand, brand_model=:brand_model, type_of_transmission = :type_of_transmission, " +
                "type_of_fuel = :type_of_fuel, vin_number = :vin_number, user_id = :user_id, car_weight = :car_weight  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CAR_BRAND, entity.getCarBrand());
        params.addValue(BRAND_MODEL,entity.getBrandModel());
        params.addValue(TYPE_OF_TRANSMISSION, entity.getTypeOfTransmission());
        params.addValue(TYPE_OF_FUEL, entity.getTypeOfFuel());
        params.addValue(VIN_NUMBER, entity.getVinNumber());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(CAR_WEIGHT, entity.getCarWeight());
        params.addValue(ID, entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_car where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Cars save(Cars entity) {
        final String sql = "INSERT INTO m_car " +
                "(car_brand, brand_model, type_of_transmission, type_of_fuel, vin_number, user_id, car_weight) " +
                "VALUES (:car_brand, :brand_model, :type_of_transmission, :type_of_fuel, :vin_number, :user_id, :car_weight)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CAR_BRAND, entity.getCarBrand());
        params.addValue(BRAND_MODEL,entity.getBrandModel());
        params.addValue(TYPE_OF_TRANSMISSION, entity.getTypeOfTransmission());
        params.addValue(TYPE_OF_FUEL, entity.getTypeOfFuel());
        params.addValue(VIN_NUMBER, entity.getVinNumber());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(CAR_WEIGHT, entity.getCarWeight());

        namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[]{ID});

        long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Cars updateOne(Cars entity) {
        final String sql = "UPDATE m_car set car_brand = :car_brand, brand_model=:brand_model, type_of_transmission = :type_of_transmission, " +
                "type_of_fuel = :type_of_fuel, vin_number = :vin_number, user_id = :user_id, car_weight = :car_weight  where id = :id";

        //KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, entity.getId());
        params.addValue(CAR_BRAND, entity.getCarBrand());
        params.addValue(BRAND_MODEL,entity.getBrandModel());
        params.addValue(TYPE_OF_TRANSMISSION, entity.getTypeOfTransmission());
        params.addValue(TYPE_OF_FUEL, entity.getTypeOfFuel());
        params.addValue(VIN_NUMBER, entity.getVinNumber());
        params.addValue(USER_ID, entity.getUserId());
        params.addValue(CAR_WEIGHT, entity.getCarWeight());

        namedParameterJdbcTemplate.update(sql, params);
        //long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(entity.getId());
    }

    /*@Override
    public List<Cars> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM m_car WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cost", cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }*/

}
