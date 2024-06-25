package org.example.oaifreeassist.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.oaifreeassist.entity.base.OaiBase;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号令牌管理
 *
 * @TableName oai_token_management
 */
@TableName(value = "oai_token_management")
@EqualsAndHashCode(callSuper = true)
@Data
public class OaiTokenManagement extends OaiBase implements Serializable {

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
    private LocalDateTime expireTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}