package account.services;

import account.models.Userx;
import account.repository.UserxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserxService {
    @Autowired
    UserxRepo userxRepo;

    public boolean checkUserxExists(String email) {

        return userxRepo.existsByEmailIgnoreCase(email);
    }

    public Userx findUserxByEmail (String email) {
        return userxRepo.findUserxByEmailIgnoreCase(email);
    }

    public Userx save(Userx userx) {
        return userxRepo.save(userx);
    }
}
