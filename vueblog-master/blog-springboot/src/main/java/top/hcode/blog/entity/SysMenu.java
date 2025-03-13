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
 * 菜单权限表
 * </p>
 *
 * @author tea
 * @since 2025-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    private String menuName;

    
    private Long parentId;

    
    private Integer orderNum;

    
    private String path;


    private String component;

    
    private String query;

    
    private String routeName;

    
    private Integer isFrame;

    
    private Integer isCache;

    
    private String menuType;

    
    private String visible;

    
    private String status;

    
    private String perms;

    
    private String icon;

    
    private String createBy;

    
    private Date createTime;

    
    private String updateBy;

    
    private Date updateTime;

    
    private String remark;


}
