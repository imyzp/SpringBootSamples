package com.yzp.spring.springbootsamples.basic.validation.valid;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ValidModel {
    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id必须大于1岁")
    @Max(value = 200, message = "id必须小于200岁")
    private Integer id;
    @NotBlank(message = "name不能为空")
    @Length(min=1,max=20,message = "name长度1到20")
    private String name;
    private List<String> nickNames;
    private List<ValidModel2> validModel2List;

}
