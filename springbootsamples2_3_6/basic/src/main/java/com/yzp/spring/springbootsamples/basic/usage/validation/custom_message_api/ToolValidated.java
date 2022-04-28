package com.yzp.spring.springbootsamples.basic.usage.validation.custom_message_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 验证信息格式化工具类
 */
public class ToolValidated {
    private static final Logger LOG = LoggerFactory.getLogger(ToolValidated.class);

    // 实际使用建议将编码信息放置在一个单独的文件中统一管理
    /**
     * 操作成功
     */
    public static int SUCCESS = 200;
    /**
     * 参数无效
     */
    public static int PARAM_INVALID = 1001;

    // =================== Spring validated (建议使用) ===================
    /**
     * <h5>功能:验证参数信息是否有效</h5>
     *
     * @param bindingResult
     * @return
     */
    public static MessageBean myValidate(BindingResult bindingResult) {
        MessageBean messageBean = new MessageBean();
        if(bindingResult.hasErrors()) {
            // 设置验证结果状态码
            messageBean.setCode(PARAM_INVALID);
            // 获取错误字段信息集合
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

            // 使用TreeSet是为了让输出的内容有序输出(默认验证的顺序是随机的)
            Set<String> errorInfoSet = new TreeSet<String>();
            for (FieldError fieldError : fieldErrorList) {
                // 遍历错误字段信息
                errorInfoSet.add(fieldError.getDefaultMessage());
                LOG.debug("[{}.{}]{}", fieldError.getObjectName() , fieldError.getField(), fieldError.getDefaultMessage());
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
}
