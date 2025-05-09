package com.GauravC4.journalApi.journalApi.controller;

import com.GauravC4.journalApi.journalApi.entity.JournalEntry;
import com.GauravC4.journalApi.journalApi.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/count")
    public int CountJournalEntries() {
        return journalService.CountEntries();
    }

    @GetMapping
    public List<JournalEntry> GetAllJournalEntries() {
        return journalService.GetAllEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry GetJournalEntryById(@PathVariable int id) {
        return journalService.GetEntryById(id);
    }

    @PostMapping
    public JournalEntry AddJournalEntry(@RequestBody JournalEntry entry) {
        return journalService.AddJournalEntry(entry);
    }

    @DeleteMapping("/{id}")
    public boolean DeleteJournalEntryById(@PathVariable int id) {
        return journalService.DeleteEntryById(id);
    }
}
