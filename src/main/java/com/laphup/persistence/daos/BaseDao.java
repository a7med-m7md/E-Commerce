package com.laphup.persistence.daos;

import java.util.Set;

public class BaseDao<Table,ID,Name> {
    public Set<Table> getAll(){return null;}
    public Table getById(ID id){return null;}
    public Table getByName(Name name){}
    public void deleteByName(Name name){}
    public void deleteById(ID name){}
}
