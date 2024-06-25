package org.example.oaifreeassist.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.oaifreeassist.entity.base.OaiBase;

import java.io.Serializable;
import java.util.Date;

/**
 * 共享账号管理
 *
 * @TableName oai_shared_management
 */
@TableName(value = "oai_shared_management")
@EqualsAndHashCode(callSuper = true)
@Data
public class OaiSharedManagement extends OaiBase implements Serializable {

    /**
     * 逻辑外键 oai_token_management.id
     */
    @TableField(value = "token_id")
    private Long tokenId;

    /**
     * 逻辑外键 oai_shared_limitation.id
     */
    @TableField(value = "limit_id")
    private Long limitId;

    /**
     * 到期时间
     */
    @TableField(value = "expiration_time")
    private Date expirationTime;

    /**
     * 共享账号名称
     */
    @TableField(value = "unique_name")
    private String uniqueName;

    /**
     * 共享账号令牌
     */
    @TableField(value = "share_token")
    private String shareToken;

    /**
     * 是否重置已用次数
     */
    @TableField(value = "is_reset_limit")
    private Integer isResetLimit;

    /**
     * 是否隔离聊天
     */
    @TableField(value = "is_isolate_chat")
    private Integer isIsolateChat;

    /**
     * 共享账号有效状态
     * 0-无效
     * 1-有效
     */
    @TableField(value = "valid_status")
    private Integer validStatus;

    /**
     * 是否开启临时聊天
     */
    @TableField(value = "is_temp_chat")
    private Integer isTempChat;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}