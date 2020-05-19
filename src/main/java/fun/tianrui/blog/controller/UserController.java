package fun.tianrui.blog.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.User;
import fun.tianrui.blog.repository.UserRepository;
import fun.tianrui.blog.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public UserVO login(@RequestBody User user) {
        System.out.println(user.toString());
        Optional<User> u = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u.isEmpty()) {
            return null;
        }
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapper.map(u.get(), UserVO.class);

    }
}
