package zim.grigory.cdd_news_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("say")
public class test {
    @GetMapping(value = "hello",produces= "text/html")
    public String sayHello () {
        return
                "<!DOCTYPE html>"+
                        "<html>"+
                        "	<head><title>Hello world!</title></head>"+
                        "	<body>Hello world!</body>"+
                        "</html>"
                ;
    }
}
