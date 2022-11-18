package account.controllers;

import account.exception.PasswordFailCheckException;
import account.exception.UnauthorizedException;
import account.exception.UserExistException;
import account.models.Userx;
import account.models.View;
import account.services.UserxService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class UserxControllers {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserxService userxService;

    private Set<String> breachPass = Set.of("PasswordForJanuary", "PasswordForFebruary",
            "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune",
            "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober",
            "PasswordForNovember", "PasswordForDecember");

    @PostMapping("/api/auth/signup")
    @JsonView(value = View.NoPassword.class)
    public ResponseEntity signup(@Validated @RequestBody Userx userx) {
        userx.setEmail(userx.getEmail().toLowerCase());
        if(userxService.checkUserxExists(userx.getEmail())) {
            throw new UserExistException("User exist!");
        }
        if(breachPass.contains(userx.getPassword())) {
            throw new PasswordFailCheckException("The password is in the hacker's database!");
        }
        userx.setPassword(passwordEncoder.encode(userx.getPassword()));
        userx.setRole("ROLE_USER");
        System.out.println("Signup.... "+userx);
        return new ResponseEntity(userxService.save(userx), HttpStatus.OK);
    }

    @GetMapping("/api/empl/payment")
    @JsonView(value = View.NoPassword.class)
    public ResponseEntity testAuthentication(Authentication auth) {
        System.out.println("test user "+auth.getName());
        Userx u = userxService.findUserxByEmail(auth.getName());

        return new ResponseEntity(u, HttpStatus.OK);
    }

    @PostMapping("/api/auth/changepass")
    public ResponseEntity changePass(Authentication auth,
                                     @RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        String changePass = body.get("new_password");

        String userName = auth.getName();
        Userx userx = userxService.findUserxByEmail(userName);
        String oldPass = userx.getPassword();

        if(changePass.length() < 12) {
            throw new PasswordFailCheckException("Password length must be 12 chars minimum!");
        }
        if(breachPass.contains(changePass)) {
            throw new PasswordFailCheckException("The password is in the hacker's database!");
        } else if(passwordEncoder.matches(changePass, oldPass)) {
            throw new PasswordFailCheckException("The passwords must be different!");
        }

        userx.setPassword(passwordEncoder.encode(changePass));
        userxService.save(userx);

        response.put("email", userName);
        response.put("status", "The password has been updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
