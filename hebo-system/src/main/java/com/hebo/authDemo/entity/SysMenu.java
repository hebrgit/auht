package com.hebo.authDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 
 * </p>
 *
 * @author hebo
 * @since 2022-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu implements GrantedAuthority {

    private static final long serialVersionUID = 1L;


    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    private Long parentId;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private Integer deleteFlag;
    @TableField(exist = false)
    List<SysMenu> sysMenus;
    @TableField(exist = false)
    private Boolean hasChildren;
    @Override
    public String getAuthority() {
        return getMenuId().toString();
    }
}
