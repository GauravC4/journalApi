package com.GauravC4.journalApi.journalApi.repository;

import com.GauravC4.journalApi.journalApi.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JournalRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int CountJournalEntries() {
        final String sql = "select count(*) from journal";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    public List<JournalEntry> GetAllJournalEntries() {
        final String sql = "select id, title, entry from journal";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JournalEntry.class));
    }

    public JournalEntry GetJournalEntryById(int id) {
        final String sql = "select id, title, entry from journal where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        try{
            return namedParameterJdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper<>(JournalEntry.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer AddJournalEntry(JournalEntry entry) {
        final String sql = "insert into journal(title, entry) values(:title, :entry) returning id";

        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(entry);

        return namedParameterJdbcTemplate.queryForObject(sql, parameters, Integer.class);
    }

    public int DeleteJournalEntryById(int id) {
        final String sql = "delete from journal where id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        return namedParameterJdbcTemplate.update(sql, parameters);
    }
}
