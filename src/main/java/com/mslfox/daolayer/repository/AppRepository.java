package com.mslfox.daolayer.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AppRepository {
    private final String product_by_name_sql;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AppRepository(@Value("${getProductName.scriptFileName}") String scriptFileName, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.product_by_name_sql = read(scriptFileName);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<String> getProductName(String name) {
        return namedParameterJdbcTemplate
                .queryForList(
                        product_by_name_sql,
                        new MapSqlParameterSource().addValue("name", name.toLowerCase()),
                        String.class);
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
