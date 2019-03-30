package com.wangjiayu.springboot.VO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TestVO {

    @NotEmpty(message = "不能为空")
    private String string;
}
