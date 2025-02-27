package service;


import dto.AddressBookDTO;
import model.AddressBookEntry;
import repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressRepository repository;

    // Get all entries
    public List<AddressBookDTO> getAllEntries() {
        return repository.findAll().stream()
                .map(entry -> new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail()))
                .collect(Collectors.toList());
    }

    // Add a new entry
    public AddressBookDTO addEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(dto.getName(), dto.getPhoneNumber(), dto.getEmail());
        AddressBookEntry savedEntry = repository.save(entry);
        return new AddressBookDTO(savedEntry.getName(), savedEntry.getPhoneNumber(), savedEntry.getEmail());
    }

    // Get an entry by ID
    public AddressBookDTO getEntryById(Long id) {
        return repository.findById(id)
                .map(entry -> new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail()))
                .orElse(null);
    }

    // Delete an entry
    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }
}