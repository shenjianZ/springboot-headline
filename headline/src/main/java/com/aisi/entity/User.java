package com.aisi.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_user
 */
//@TableName(value ="news_user")
@Data
public class User implements Serializable {
    @TableId
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}