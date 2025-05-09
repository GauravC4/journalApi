package com.GauravC4.journalApi.journalApi.controller;

import com.GauravC4.journalApi.journalApi.entity.JournalEntry;
import com.GauravC4.journalApi.journalApi.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<JournalEntry>  GetJournalEntryById(@PathVariable int id) {
        Optional<JournalEntry> journalEntry = journalService.GetEntryById(id);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> AddJournalEntry(@RequestBody JournalEntry entry) {
        Optional<JournalEntry> journalEntry = journalService.AddJournalEntry(entry);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public boolean DeleteJournalEntryById(@PathVariable int id) {
        return journalService.DeleteEntryById(id);
    }
}
