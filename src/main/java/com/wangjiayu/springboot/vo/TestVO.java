package com.wangjiayu.springboot.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "TestVO测试")
public class TestVO {

    @NotEmpty(message = "{testVO.message}")
    private String string;
    @NotEmpty(message = "{testVO.message}")
    private String string1;
}
