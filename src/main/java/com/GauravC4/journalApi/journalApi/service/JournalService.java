package com.GauravC4.journalApi.journalApi.service;

import com.GauravC4.journalApi.journalApi.entity.JournalEntry;
import com.GauravC4.journalApi.journalApi.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public JournalEntry GetEntryById(int id) {
        if(id < 1) return new JournalEntry();
        JournalEntry entry = journalRepository.GetJournalEntryById(id);
        if(entry != null) return entry;
        return new JournalEntry();
    }

    @Transactional
    public JournalEntry AddJournalEntry(JournalEntry entry) {
        Integer id = journalRepository.AddJournalEntry(entry);
        if(id == null) {
            return new JournalEntry();
        }
        return journalRepository.GetJournalEntryById(id);
    }

    public boolean DeleteEntryById(int id) {
        if(id < 1) return false;
        return journalRepository.DeleteJournalEntryById(id) > 0;
    }
}
