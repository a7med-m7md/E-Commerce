package com.laphup.persistence.daos;

public interface BaseDao<ID,Name> {
    public void getAll();
    public void getById(ID id);
    public void getByName(Name name);
    public void deleteByName(Name name);
    public void deleteById(ID name);
}
