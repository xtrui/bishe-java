package fun.tianrui.blog.utils;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    private final static String salt = "sdaf6546y65l;uq234o;i";

    public static String getMd5(String message) {
        String base = message + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
