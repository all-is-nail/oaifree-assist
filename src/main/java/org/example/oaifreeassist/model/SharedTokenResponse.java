package org.example.oaifreeassist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SharedTokenResponse {

    @JsonProperty("expire_at")
    private Integer expireAt;

    @JsonProperty("gpt35_limit")
    private Integer gpt35Limit;

    @JsonProperty("gpt4_limit")
    private Integer gpt4Limit;

    @JsonProperty("reset_limit")
    private Boolean resetLimit;

    @JsonProperty("show_conversations")
    private Boolean showConversations;

    @JsonProperty("site_limit")
    private String siteLimit;

    @JsonProperty("temporary_chat")
    private Boolean temporaryChat;

    @JsonProperty("token_key")
    private String tokenKey;

    @JsonProperty("unique_name")
    private String uniqueName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_id")
    private String userId;
}
