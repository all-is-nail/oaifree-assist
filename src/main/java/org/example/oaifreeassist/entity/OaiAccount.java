package org.example.oaifreeassist.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.example.oaifreeassist.entity.base.OaiBase;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OAI 账号信息
 */
@TableName(value = "oai_account")
@Data
public class OaiAccount extends OaiBase implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}