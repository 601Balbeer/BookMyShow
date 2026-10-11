package com.bookmyshow.services;

import com.bookmyshow.models.GlobalRecord;

public interface EntityService<T>{
    public T saveOrUpdate(T record);
}
