package com.unkownkoder.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

	@Column(unique=true)
    private String username;

    private String password;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TodoList> todoLists = new ArrayList<>();


    public User() {
		super();
		authorities = new HashSet<>();
	}

	public User(int id, String username, String password, Set<Role> authorities, boolean accountNonLocked){

		this.username = username;
		this.password = password;
		this.authorities = authorities;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	/* If you want account locking capabilities create variables and ways to set them for the methods below */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    
}
