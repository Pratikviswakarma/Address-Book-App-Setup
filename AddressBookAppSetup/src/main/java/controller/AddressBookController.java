package controller;


import dto.AddressBookDTO;
import service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping
    public ResponseEntity<List<AddressBookDTO>> getAllEntries() {
        return ResponseEntity.ok(service.getAllEntries());
    }

    @PostMapping
    public ResponseEntity<AddressBookDTO> addEntry(@RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.addEntry(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDTO> getEntryById(@PathVariable Long id) {
        AddressBookDTO entry = service.getEntryById(id);
        return (entry != null) ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookDTO> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        AddressBookDTO updatedEntry = service.updateEntry(id, dto);
        return (updatedEntry != null) ? ResponseEntity.ok(updatedEntry) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        return service.deleteEntry(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}



