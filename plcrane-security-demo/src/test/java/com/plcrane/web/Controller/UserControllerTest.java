package com.plcrane.web.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

//让测试运行于Spring测试环境
@RunWith(SpringRunner.class)
//说明这是一个springboot测试类
@SpringBootTest
public class UserControllerTest {
    //伪造的是一个web  要伪造一个spring mvc 环境
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    //服务启动执行 @Before
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        System.out.println("=====开始！！！！=====");
    }

    //用户查询测试用例
    @Test
    public void whenQuerySuccess() throws Exception {
       String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","jojo")
                .param("age","18")
                .param("ageTo","20")
                .param("xxx","yyy")
//                .param("size","15")
//                .param("page","3")
//                .param("sort","age,desc")

        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //https://github.com/json-path/JsonPath  jsonPath 的作用
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void whenGenInfoSuccess() throws Exception{
        String  result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    @Test
    public void whenGenInfoFail() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


    @Test
    public void whenCreateSuccess() throws Exception{

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String reuslt=mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(reuslt);
    }



}
