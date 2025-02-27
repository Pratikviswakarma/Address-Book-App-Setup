package service;


import dto.AddressBookDTO;
import model.AddressBookEntry;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private final Map<Long, AddressBookEntry> addressBook = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Get all entries
    public List<AddressBookDTO> getAllEntries() {
        return addressBook.values().stream()
                .map(entry -> new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail()))
                .collect(Collectors.toList());
    }

    // Add a new entry
    public AddressBookDTO addEntry(AddressBookDTO dto) {
        Long id = idCounter.getAndIncrement();
        AddressBookEntry entry = new AddressBookEntry(id, dto.getName(), dto.getPhoneNumber(), dto.getEmail());
        addressBook.put(id, entry);
        return new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail());
    }

    // Get an entry by ID
    public AddressBookDTO getEntryById(Long id) {
        AddressBookEntry entry = addressBook.get(id);
        return (entry != null) ? new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail()) : null;
    }

    // Update an entry by ID
    public AddressBookDTO updateEntry(Long id, AddressBookDTO dto) {
        AddressBookEntry entry = addressBook.get(id);
        if (entry != null) {
            entry.setName(dto.getName());
            entry.setPhoneNumber(dto.getPhoneNumber());
            entry.setEmail(dto.getEmail());
            return new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail());
        }
        return null;
    }

    // Delete an entry by ID
    public boolean deleteEntry(Long id) {
        return addressBook.remove(id) != null;
    }
}