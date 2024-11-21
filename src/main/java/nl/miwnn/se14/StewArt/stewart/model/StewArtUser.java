package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Luc Weerts
 * A person able to log in to our website
 */
@Entity
public class StewArtUser implements UserDetails {

    private String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;

    private String role;

//    public StewArtUser(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }

    @ManyToMany(mappedBy = "likedByUserSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //deleting user deletes all recipes
    private Set<Recipe> likedRecipes;

    @OneToMany(mappedBy = "recipeAuthor")
    private Set<Recipe> myRecipes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
//        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Recipe> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(Set<Recipe> recipes) {
        this.likedRecipes = recipes;
    }

    public Set<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(Set<Recipe> myRecipes) {
        this.myRecipes = myRecipes;
    }

    public String getROLE_PREFIX() {
        return ROLE_PREFIX;
    }

    public void setROLE_PREFIX(String ROLE_PREFIX) {
        this.ROLE_PREFIX = ROLE_PREFIX;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
