package com.yzp.spring.springbootsamples.basic.validation.custom_message_api.controller;

import com.yzp.spring.springbootsamples.basic.validation.custom_message_api.MessageBean;
import com.yzp.spring.springbootsamples.basic.validation.custom_message_api.ToolInterface;
import com.yzp.spring.springbootsamples.basic.validation.custom_message_api.ToolValidated;
import com.yzp.spring.springbootsamples.basic.validation.model.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h5>描述:实体类参数校验示例</h5>
 *  手动校验
 */
@RestController
@RequestMapping("test")
public class MessageController {
    /**
     * <h5>功能:只验证未UserVo中设置group的属性</h5>
     * 	只验证不需要分组的参数,这里只验证userVo.age
     * @param userVo
     * @param bindingResult
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("validatedOne")
    public MessageBean validatedOne(@Validated() UserVo userVo, BindingResult bindingResult) {
        // 验证参数信息是否有效
        MessageBean messageBean = ToolValidated.myValidate(bindingResult);
        if (ToolValidated.SUCCESS == messageBean.getCode()) {
            messageBean.setMessage("哈哈哈,通过验证了");
        }

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中group为"ToolValidated.insert.class"的属性</h5>
     * 	验证组为"insert.class"的参数,这里验证userVo.name、验证userVo.password
     * @param userVo
     * @param bindingResult
     * @return
     */
    @RequestMapping("validatedTwo")
    public MessageBean validatedTwo(@Validated(ToolInterface.insert.class) UserVo userVo, BindingResult bindingResult) {
        // 验证参数信息是否有效
        MessageBean messageBean = ToolValidated.myValidate(bindingResult);
        if (ToolValidated.SUCCESS == messageBean.getCode()) {
            messageBean.setMessage("哈哈哈,通过验证了");
        }

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中group为"ToolValidated.update.class"的属性</h5>
     * 	验证组为"update.class"的参数,这里验证userVo.id、userVo.name、验证userVo.password
     * @param userVo
     * @param bindingResult
     * @return
     */
    @RequestMapping("validatedThree")
    public MessageBean validatedThree(@Validated(ToolInterface.update.class) UserVo userVo, BindingResult bindingResult) {
        // 验证参数信息是否有效
        MessageBean messageBean = ToolValidated.myValidate(bindingResult);
        if (ToolValidated.SUCCESS == messageBean.getCode()) {
            messageBean.setMessage("哈哈哈,通过验证了");
        }

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中同时满足多个group的属性</h5>
     * 	验证组为"all.class"的参数,all接口包含insert.class和 update.class,
     * 	因此需要验证同时属于insert.class和 update.class的参数,
     * 	这里只验证userVo.name、验证userVo.password
     * @param userVo
     * @param bindingResult
     * @return
     */
    @RequestMapping("validatedFour")
    public MessageBean validatedFour(@Validated(ToolInterface.all.class) UserVo userVo, BindingResult bindingResult) {
        // 验证参数信息是否有效
        MessageBean messageBean = ToolValidated.myValidate(bindingResult);
        if (ToolValidated.SUCCESS == messageBean.getCode()) {
            messageBean.setMessage("哈哈哈,通过验证了");
        }

        return messageBean;
    }
}
