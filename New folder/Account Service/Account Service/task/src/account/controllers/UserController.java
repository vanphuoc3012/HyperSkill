package account.controllers;

import account.models.UserX;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/api/auth/signup")
    public ResponseEntity signup(@Validated @RequestBody UserX userX) {

        return new ResponseEntity(userX, HttpStatus.OK);
    }
}
