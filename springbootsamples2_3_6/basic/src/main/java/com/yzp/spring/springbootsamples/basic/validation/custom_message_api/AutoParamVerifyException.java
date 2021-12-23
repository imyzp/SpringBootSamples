package com.yzp.spring.springbootsamples.basic.validation.custom_message_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.TreeSet;

/**
 * <h5>描述:全局参数验证异常处理</h5>
 * 设定执行顺序,只要比全局异常处理类靠前就行,否则会报500或者404错误信息
 * 这里的全局异常处理类是MyExceptionHandler.java
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class AutoParamVerifyException {
    private static final Logger LOG = LoggerFactory.getLogger(AutoParamVerifyException.class);

    /**
     * <h5>功能:处理普通参数校验失败的异常</h5>
     *
     * @param ex ConstraintViolationException
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public MessageBean ConstraintViolationException(ConstraintViolationException ex) {
        MessageBean messageBean = new MessageBean();
        // 使用TreeSet是为了让输出的内容有序输出(默认验证的顺序是随机的)
        Set<String> errorInfoSet = new TreeSet<String>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        if (!violations.isEmpty()) {
            // 设置验证结果状态码
            messageBean.setCode(ToolValidated.PARAM_INVALID);
            for (ConstraintViolation<?> item : violations) {
                System.out.println(item.getPropertyPath());
                // 遍历错误字段信息
                errorInfoSet.add(item.getMessage());
                LOG.debug("[{}]{}", item.getPropertyPath(), item.getMessage());
            }

            StringBuffer sbf = new StringBuffer();
            for (String errorInfo : errorInfoSet) {
                sbf.append(errorInfo);
                sbf.append(",");
            }
            messageBean.setMessage(sbf.substring(0, sbf.length() - 1));
        }

        return messageBean;
    }

    /**
     * <h5>功能: 处理实体类参数校验失败的异常</h5>
     *
     * @param
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public MessageBean BindException(BindException bindingResult) {
        // 验证参数信息是否有效
        MessageBean messageBean = ToolValidated.myValidate(bindingResult);
        return messageBean;
    }
}
