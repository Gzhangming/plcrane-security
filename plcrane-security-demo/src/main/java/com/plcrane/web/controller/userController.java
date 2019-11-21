package com.plcrane.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.plcrane.dto.User;
import com.plcrane.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

//标明此Controller提供RestAPI
@RestController
@RequestMapping("/user")
public class userController {

    //变体 映射HTTP请求URL到java方法
    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    //@RequestParam 要求传来参数要带一个username 不然会报错
    //name 别名  required 参数是否必填
    //@RequestParam(name = "username",required = false,defaultValue = "tom") String username
    //@PageableDefault() Pageable pageable  分页  @ 可以指定默认值
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(page = 1,size = 5,sort = "username,asc") Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(userQueryCondition));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());


        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    //@PathVariable 映射变量
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id){

        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }


    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }


        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }






}
