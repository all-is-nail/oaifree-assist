package org.example.oaifreeassist.model;

import lombok.Data;

@Data
public class SharedTokenDTO {
    // private String user_id;
    // private String user_email;
    private String unique_name;
    private String access_token;
    // private String token_key;
    private Integer expires_in;
    // private Integer expires_at;
    private String site_limit;
    private Integer gpt35_limit;
    private Integer gpt4_limit;
    private Boolean show_conversations;
    private Boolean temporary_chat;
    private Boolean reset_limit;
}
