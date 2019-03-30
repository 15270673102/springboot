package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.VO.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "index")
public class IndexController {

    @ApiOperation(value = "test1", notes = "test1")
    @GetMapping(value = "test1")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "string", value = "string", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "string1", value = "string1", required = false, dataType = "String", paramType = "query")}
    )
    public void test1(@Valid TestVO testVO) {
        System.out.println(testVO);
    }


}
