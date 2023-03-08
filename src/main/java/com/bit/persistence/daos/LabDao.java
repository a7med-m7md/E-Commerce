package com.bit.persistence.daos;

import com.bit.persistence.entities.LabCategory;
import com.bit.util.enums.SortBy;

public interface LabDao {
    public void getAll(LabCategory labCategory, SortBy sortedBy);
}
