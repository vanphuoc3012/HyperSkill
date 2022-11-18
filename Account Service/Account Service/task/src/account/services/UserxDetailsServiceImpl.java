package account.services;

import account.models.Userx;
import account.models.UserxDetailsImpl;
import account.repository.UserxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserxDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserxRepo userxRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userx u = userxRepo.findUserxByEmailIgnoreCase(username);
        if(u == null) {
            throw new UsernameNotFoundException("Username not found: "+username);
        }

        return new UserxDetailsImpl(u);
    }
}
