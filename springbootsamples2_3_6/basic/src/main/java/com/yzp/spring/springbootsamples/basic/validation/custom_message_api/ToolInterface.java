package com.yzp.spring.springbootsamples.basic.validation.custom_message_api;

import javax.validation.GroupSequence;

/**
 * 分组使用,不分组的话,不需要创建接口,可直接跳过此步
 */
public interface ToolInterface {
    // 新增使用(配合spring的@Validated功能分组使用)
     interface insert{}

    // 更新使用(配合spring的@Validated功能分组使用)
     interface update{}

    // 属性必须有这两个分组的才验证(配合spring的@Validated功能分组使用)
    @GroupSequence({insert.class, update.class})
     interface all{};
}
