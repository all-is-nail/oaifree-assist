// src/main/java/com/example/demo/controller/WebPageController.java
package org.example.oaifreeassist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/accounts")
    public String getAccountListPage() {
        return "account/list";
    }
}
