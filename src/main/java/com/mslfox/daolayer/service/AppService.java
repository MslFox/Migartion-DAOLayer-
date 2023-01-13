package com.mslfox.daolayer.service;

import com.mslfox.daolayer.repository.AppRepository;
import org.springframework.stereotype.Service;

@Service

public class AppService {
    private final AppRepository repository;

    public AppService(AppRepository repository) {
        this.repository = repository;
    }

    public String getProductNameByName(String name) {
        var resultList = repository.getProductName(name);
        return resultList.isEmpty() ? name + " has no orders!" :
                String.format("PRODUCTS IN %s'S ORDER:\n%s", name.toUpperCase(),
                        String.join("\n", resultList));
    }
}
