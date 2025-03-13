package top.hcode.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author tea
 * @since 2025-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    
    private Long deptId;

    
    private String userName;

    
    private String nickName;

    
    private String userType;

    
    private String email;

    
    private String phonenumber;

    
    private String sex;

    
    private String avatar;

    
    private String password;

    
    private String status;

    
    private String delFlag;

    
    private String loginIp;

    
    private Date loginDate;

    
    private String createBy;

    
    private Date createTime;

    
    private String updateBy;

    
    private Date updateTime;

    
    private String remark;


}
