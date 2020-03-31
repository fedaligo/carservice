package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Users;

import java.util.List;

public interface UsersDao extends GenericDao<Users,Long> {

    public List<Users> findAllDeletedUsers();
    public List<Users> createdAfter();
    public List<Long> batchUpdate(List<Users> users);
    public Users findByLogin(String login);
    public Users save(Users entity);
    public Users updateOne(Users entity);
    public List<Users> search(String query, Long limit, Long offset);
    //public Users createOne(Users entity);

    /* public void create(final Users entity);
    public List<Users> readAll();
    public Users readById(Long id);
    public Users updateById(Users entity);
    public void deleteById(Long id);*/

    /*public List<Users> findAll();
    public String findNameById(int id);
    public Employee findById_1(int id);
    public Employee findById(int id);
    public void insertBatch1(final List<Employee> employees);
    //public List<Long> batchUpdate(List<Employee> employees);
    public void insertBatch2(final String sql);
    public void delete(int id);
    public Employee updateById(Employee employee);
    public List<Employee> search(String query);*/
}
