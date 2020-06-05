package fun.tianrui.blog.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.EmailAndCode;
import fun.tianrui.blog.entity.User;
import fun.tianrui.blog.repository.EmailAndCodeRepository;
import fun.tianrui.blog.repository.UserRepository;
import fun.tianrui.blog.utils.EmailUtils;
import fun.tianrui.blog.utils.MD5Utils;
import fun.tianrui.blog.vo.RegisterVO;
import fun.tianrui.blog.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final EmailAndCodeRepository emailAndCodeRepository;
    private final EmailUtils emailUtils;

    @Autowired
    public UserController(UserRepository userRepository, EmailAndCodeRepository emailAndCodeRepository, EmailUtils emailUtils) {
        this.userRepository = userRepository;
        this.emailAndCodeRepository = emailAndCodeRepository;
        this.emailUtils = emailUtils;
    }

    @PostMapping("login")
    public UserVO login(@RequestBody User user) {
        user.setPassword(MD5Utils.getMd5(user.getPassword()));
        Optional<User> u = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u.isEmpty()) {
            return null;
        }
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapper.map(u.get(), UserVO.class);
    }

    /**
     * @param email 邮箱
     * @return 验证码，或者错误信息
     */
    @GetMapping("/getCode")
    public Map geCode(String email) {
        HashMap<Object, Object> map = new HashMap<>();

        //判断是否为正确邮箱
        if (EmailUtils.isEmail(email)) {
            //看数据库存不存在该邮箱
            if (userRepository.findByEmail(email).isEmpty()) {
                //生成验证码发送
                String Code = null;
                EmailAndCode emailAndCode = new EmailAndCode();
                try {
                    Code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                    emailUtils.send(email, Code);
                    emailAndCode.setCode(Code);
                    emailAndCode.setEmail(email);
                    emailAndCodeRepository.save(emailAndCode);
                    map.put("code", Code);
                    map.put("status", 200);
                    map.put("email", email);
                } catch (Exception e) {
                    map.put("status", 600);
                    map.put("message", "发送失败");
                    e.printStackTrace();
                }

            }
        } else {
            map.put("status", 600);
            map.put("message", "邮箱格式不正确");
        }
        //判断邮箱是否已被注册

        return map;
    }

    @PostMapping("/register")
    public Map<Object, Object> register(@RequestBody RegisterVO registerVO) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        User user = mapper.map(registerVO, User.class);
        user.setEmail(registerVO.getEmail());
        user.setPassword(MD5Utils.getMd5(registerVO.getPassword()));
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        HashMap<Object, Object> map = new HashMap<>();
        if (userOptional.isEmpty()) {
            user.setRole(false);
            userRepository.save(user);
            map.put("code", "200");
            map.put("message", "注册成功");
        } else {
            map.put("code", "600");
            map.put("message", "用户已存在");
        }
        return map;
    }
}
