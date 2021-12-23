package com.yzp.spring.springbootsamples.basic.validation.custom_message_api.controller;

import com.yzp.spring.springbootsamples.basic.validation.custom_message_api.MessageBean;
import com.yzp.spring.springbootsamples.basic.validation.custom_message_api.ToolInterface;
import com.yzp.spring.springbootsamples.basic.validation.model.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 自动校验
 */
@RestController
@RequestMapping("test2")
@Validated    // @Validated只能作用在类上面
public class MessageAutoController {
    /**
     * <h5>功能:只验证未UserVo中设置group的属性</h5>
     *
     * @param userVo
     * @return
     */
    @RequestMapping("validatedOne")
    public MessageBean validatedOne(@Validated() UserVo userVo) {
        MessageBean messageBean = new MessageBean("哈哈哈,通过验证了");

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中group为"insert.class"的属性</h5>
     *
     * @param userVo
     * @return
     */
    @RequestMapping("validatedTwo")
    public MessageBean validatedTwo(@Validated(ToolInterface.insert.class) UserVo userVo) {
        MessageBean messageBean = new MessageBean("哈哈哈,通过验证了");

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中group为"update.class"的属性</h5>
     *
     * @param userVo
     * @return
     */
    @RequestMapping("validatedThree")
    public MessageBean validatedThree(@Validated(ToolInterface.update.class) UserVo userVo) {
        MessageBean messageBean = new MessageBean("哈哈哈,通过验证了");

        return messageBean;
    }

    /**
     * <h5>功能:只验证UserVo中同时满足多个group的属性(一般不使用这个)</h5>
     * all.class中同时包含了insert.class和update.class
     * @param userVo
     * @return
     */
    @RequestMapping("validatedFour")
    public MessageBean validatedFour(@Validated(ToolInterface.all.class) UserVo userVo) {
        MessageBean messageBean = new MessageBean("哈哈哈,通过验证了");

        return messageBean;
    }

    /**
     * <h5>功能:直接参数校验</h5>
     * 验证框架里面大部分都不需要我们显示设置message，每个注解框架都给了一个默认提示语，大多数提示还都比较友好
     * 不建议对原始类型数据如int进行参数校验(支持不好,会报异常),建议绑定实体参数校验,如上面几个方法的校验方式
     * @param name
     * @param
     * @return
     */
    @RequestMapping("validatedFive")
    public MessageBean validatedFive(
            @NotBlank(message = "姓名不能为空") String name,
            @Min(value = 10, message = "年龄必须大于10岁") Integer age
    ) {
        MessageBean messageBean = new MessageBean("哈哈哈,通过验证了");

        return messageBean;
    }
}
