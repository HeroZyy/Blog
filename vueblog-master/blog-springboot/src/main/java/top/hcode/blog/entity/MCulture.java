package top.hcode.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 1
 * @since 2025-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MCulture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String title;

    private String description;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDate gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDate gmtModified;
    private Integer status;

    @TableLogic
    private Integer deleted;
    private String column1;
    private String column2;
    private String column3;

    private String author;

    private String img;

    private String bclass;


}
