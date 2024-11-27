package nl.miwnn.se14.StewArt.stewart.service;

import nl.miwnn.se14.StewArt.stewart.dto.StewArtUserDTO;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.StewArtUserRepository;
import nl.miwnn.se14.StewArt.stewart.service.mapper.StewArtUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Luc Weerts
 * Handles interaction with StewArtUserRepository for security purposes
 */
@Service
public class StewArtUserService implements UserDetailsService {
    private final StewArtUserRepository stewArtUserRepository;
    private final PasswordEncoder passwordEncoder;

    public StewArtUserService(StewArtUserRepository stewArtUserRepository, PasswordEncoder passwordEncoder) {
        this.stewArtUserRepository = stewArtUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return stewArtUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Could not find user %s.", username)
                ));
    }

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public List<StewArtUser> getAllUsers() {
        return stewArtUserRepository.findAll();
    }

    public void save(StewArtUserDTO userDTO) {
        save(StewArtUserMapper.fromDTO(userDTO));
    }

    public void save(StewArtUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        stewArtUserRepository.save(user);
    }

    public void deleteById(Long userId) {
        stewArtUserRepository.deleteById(userId);
    }

    public boolean usernameInUse(String username) {
        return stewArtUserRepository.findByUsername(username).isPresent();
    }
}
