package platform.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.Code;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class returnAPI {



    @GetMapping("/code")
    public ResponseEntity codeJson(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        Code code = new Code("public static void main(String[] args) {\\n" +
                "        SpringApplication.run(CodeSharingPlatform.class, args);\\n" +
                "    }");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(code);
    }

    @PostMapping("/code/new")
    public ResponseEntity addNewCode(@RequestBody Code codeToAdd){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        Code code = new Code();
        code.setCode(codeToAdd.getCode());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body("{}");

    }


}
