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

    public List<AddressBookDTO> getAllEntries() {
        return repository.findAll().stream()
                .map(entry -> new AddressBookDTO(entry.getName(), entry.getPhoneNumber(), entry.getEmail()))
                .collect(Collectors.toList());
    }

    public AddressBookDTO addEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(dto.getName(), dto.getPhoneNumber(), dto.getEmail());
        AddressBookEntry savedEntry = repository.save(entry);
        return new AddressBookDTO(savedEntry.getName(), savedEntry.getPhoneNumber(), savedEntry.getEmail());
    }
}