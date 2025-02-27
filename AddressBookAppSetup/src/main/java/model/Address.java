package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    
    public String getName() {
    	return this.name;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPhone() {
    	return this.phone;
    }
    
    public void setName(String name) {
    	this.name=name;
    }
    
    public void setEmail(String email) {
    	this.email=email;
    }
    
    public void setPhone(String phone) {
    	this.phone=phone;
    }
    
}

