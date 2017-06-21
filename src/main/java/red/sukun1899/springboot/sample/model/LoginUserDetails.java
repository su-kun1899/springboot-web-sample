package red.sukun1899.springboot.sample.model;

import org.springframework.security.core.authority.AuthorityUtils;
import red.sukun1899.springboot.sample.entity.User;

/**
 * @author su-kun1899
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
