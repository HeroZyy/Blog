package top.hcode.blog.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description:
 */
@Data
public class LoginDto implements Serializable {
//    @NotBlank(message = "用户名不能为空")
    private String user_name;

//    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}