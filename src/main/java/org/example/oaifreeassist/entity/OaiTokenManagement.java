package org.example.oaifreeassist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号令牌管理
 *
 * @TableName oai_token_management
 */
@TableName(value = "oai_token_management")
@Data
public class OaiTokenManagement implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 逻辑外键 oai_account.id
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * token类型：
     * 1-refresh token 2-access token 3-shared token
     */
    @TableField(value = "token_type")
    private Integer tokenType;

    /**
     * token 值
     */
    @TableField(value = "token_value")
    private String tokenValue;

    /**
     * 到期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}