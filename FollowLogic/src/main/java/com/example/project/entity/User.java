package com.example.project.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@Column(name="first_name" , nullable = false, length=20)
	private String firstName;
	
	@Column(name="last_name" , nullable = false, length=20)
	private String lastName;
	
	@Column(name="mobile_number" , nullable = false, length=20)
	private String mobileNumber;
	
	// this is used for authentication
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	
    private String password;
	
	@ManyToMany
	@JoinTable(name = "user_followers", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "follower_id"))
	@JsonManagedReference 
	private List<User> followers;

	// Add a self-referencing Many-to-Many relationship for following
	@ManyToMany(mappedBy = "followers")
	@JsonBackReference
	private List<User> following;
	
	
	
}