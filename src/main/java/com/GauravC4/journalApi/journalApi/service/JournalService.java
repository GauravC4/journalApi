package com.GauravC4.journalApi.journalApi.service;

import com.GauravC4.journalApi.journalApi.entity.JournalEntry;
import com.GauravC4.journalApi.journalApi.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public int CountEntries() {
        return journalRepository.CountJournalEntries();
    }

    public List<JournalEntry> GetAllEntries() {
        return journalRepository.GetAllJournalEntries();
    }

    public Optional<JournalEntry> GetEntryById(int id) {
        if(id < 1) return Optional.empty();
        return journalRepository.GetJournalEntryById(id);
    }

    @Transactional
    public Optional<JournalEntry> AddJournalEntry(JournalEntry entry) {
        Integer id = journalRepository.AddJournalEntry(entry);
        if(id == null) {
            return Optional.empty();
        }
        return journalRepository.GetJournalEntryById(id);
    }

    public boolean DeleteEntryById(int id) {
        if(id < 1) return false;
        return journalRepository.DeleteJournalEntryById(id) > 0;
    }
}
