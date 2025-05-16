package com.GauravC4.journalApi.journalApi.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JournalEntry {

    private long id;

    private String title;

    private String entry;

    private LocalDateTime created_on;

    private LocalDateTime modified_on;
}
