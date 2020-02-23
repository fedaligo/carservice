package com.htp.dao.impl;

import com.htp.dao.CarsDao;
import com.htp.entity.Cars;
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

@Repository("CarsDaoImpl")
@Transactional
public class CarsDaoImpl implements CarsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //getEmployeeRowMapper парсит resultset
    private Cars getEmployeeRowMapper(ResultSet resultSet, int i) throws SQLException {
        Cars cars = new Cars();
        cars.setId(resultSet.getLong("id"));
        cars.setCarBrand(resultSet.getString("car_brand"));
        cars.setTypeOfTransmission(resultSet.getString("type_of_transmission"));
        cars.setTypeOfFuel(resultSet.getString("type_of_fuel"));
        cars.setVinNumber(resultSet.getString("vin_number"));
        cars.setUserId(resultSet.getLong("user_id"));
        cars.setCarWeight(resultSet.getLong("car_weight"));
        return cars;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void create(Cars entity) {

        String sql = "INSERT INTO m_car " +
                "(id, car_brand, type_of_transmission, type_of_fuel, vin_number, user_id, car_weight) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, this::getEmployeeRowMapper);
    }

   /* @Override
    public Users readById(int id) {
        return null;
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<Cars> update(Cars entity) {
        final String sql = "UPDATE m_car set car_brand = :car_brand, type_of_transmission = :type_of_transmission, " +
                "type_of_fuel = :type_of_fuel, vin_number = :vin_number, user_id = :user_id, car_weight = :car_weight  where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("car_brand", entity.getCarBrand());
        params.addValue("type_of_transmission", entity.getTypeOfTransmission());
        params.addValue("type_of_fuel", entity.getTypeOfFuel());
        params.addValue("vin_number", entity.getVinNumber());
        params.addValue("user_id", entity.getUserId());
        params.addValue("car_weight", entity.getCarWeight());
        params.addValue("id", entity.getId());
        namedParameterJdbcTemplate.update(sql, params);
        return findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Long id) {
        final String sql = "delete from m_car where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);

    }

    /*@Override
    public List<Cars> trackingByHigherCost(Long cost) {
        String sql = "SELECT * FROM m_car WHERE cost >= :cost";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cost", cost);
        return namedParameterJdbcTemplate.query(sql, params, this::getEmployeeRowMapper);
    }*/

}
