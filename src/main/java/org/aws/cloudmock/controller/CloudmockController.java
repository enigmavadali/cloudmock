package org.aws.cloudmock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloudmock")
public class CloudmockController {

    @GetMapping("/ping")
    public String ping(){
        return "CloudMock up and running";
    }
}
