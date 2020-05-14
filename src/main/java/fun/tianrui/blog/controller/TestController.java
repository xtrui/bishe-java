package fun.tianrui.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TIANRUI
 */
@RestController
public class TestController {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("ok");
        return "test OK";
    }
}
