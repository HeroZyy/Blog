package top.hcode.blog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
//    private Long user_id;
    private String user_name;
    private String avatar;
    private String email;
}