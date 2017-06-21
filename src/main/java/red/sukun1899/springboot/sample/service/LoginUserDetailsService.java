package red.sukun1899.springboot.sample.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import red.sukun1899.springboot.sample.entity.User;
import red.sukun1899.springboot.sample.model.LoginUserDetails;
import red.sukun1899.springboot.sample.repository.UserRepository;

/**
 * @author su-kun1899
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public LoginUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOne(userName);
        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found. userName: " + userName);
        }

        return new LoginUserDetails(user);
    }
}
