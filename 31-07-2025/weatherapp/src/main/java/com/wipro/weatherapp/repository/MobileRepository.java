package com.wipro.weatherapp.repository;

import com.wipro.weatherapp.dto.Mobile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MobileRepository {
    private final List<Mobile> mobileList = new ArrayList<>();

    public void save(Mobile mobile) {
        mobileList.add(mobile);
    }

    public List<Mobile> findAll() {
        return mobileList;
    }

    public Optional<Mobile> findById(int id) {
        return mobileList.stream().filter(m -> m.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        mobileList.removeIf(m -> m.getId() == id);
    }

    public void update(Mobile mobile) {
        deleteById(mobile.getId());
        save(mobile);
    }
}
