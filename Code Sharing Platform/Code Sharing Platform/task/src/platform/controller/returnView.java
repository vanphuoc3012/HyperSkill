package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import platform.models.Code;

@Controller
public class returnView {
    @GetMapping("/code")
    public String getCode(Model model){
        Code testCode = new Code("public static void main(String[] args) {\n" +
                "        SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "    }");
        model.addAttribute("code", testCode);
        return "code";
    }

    @GetMapping("/code/new")
    public String newCode(){
        return "newCode";
    }
}
