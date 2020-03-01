package com.htp.dao;

import com.htp.dao.GenericDao;
import com.htp.entity.Users;

import java.util.List;

public interface UsersDao extends GenericDao<Users,Long> {

    public List<Users> findAllDeletedUsers();
    public List<Users> createdAfter();
    public List<Long> batchUpdate(List<Users> users);
    public Users findByLogin(String login);

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
