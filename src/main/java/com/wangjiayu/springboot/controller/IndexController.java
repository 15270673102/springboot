package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.VO.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Api(tags = "index")
@Validated
public class IndexController {

    @ApiOperation(value = "test1", notes = "test1")
    @GetMapping(value = "test1")
    @ApiImplicitParam(name = "string", value = "string", required = false, dataType = "TestVO", paramType = "query")
    public void test1(@Valid TestVO testVO) {
        System.out.println(testVO);
    }


}
