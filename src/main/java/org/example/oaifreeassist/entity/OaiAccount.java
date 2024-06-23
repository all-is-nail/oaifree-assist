package org.example.oaifreeassist.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OAI 账号信息
 */
@TableName(value = "oai_account")
@Data
public class OaiAccount implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号邮箱
     */
    private String email;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 账号有效状态
     * 0-无效 1-有效
     */
    private Integer validStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}