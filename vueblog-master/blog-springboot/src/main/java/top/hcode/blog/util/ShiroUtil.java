package top.hcode.blog.util;

import org.apache.shiro.SecurityUtils;
import top.hcode.blog.shiro.AccountProfile;

/**
 * @Description:
 */
public class ShiroUtil {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}