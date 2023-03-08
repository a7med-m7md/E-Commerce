package com.bit.persistence.daos;

public abstract class BaseDao<ID,Name> {
    public abstract void getAll();
    public abstract void getById(ID id);
    public abstract void getByName(Name name);
    public abstract void deleteByName(Name name);
    public abstract void deleteById(ID name);
}
