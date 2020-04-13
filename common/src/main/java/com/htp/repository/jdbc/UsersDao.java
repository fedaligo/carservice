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
    public void create(Users entity);
}
