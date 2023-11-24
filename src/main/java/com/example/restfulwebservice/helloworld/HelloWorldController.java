package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //GET
    //uri /hello-world (end point)
    //기존에는 @RequestMapping을 사용함
    //@RequestMapping는 @ReuqsetMapping(method , path)를 지정해 줘야 한다.
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
         return "hello world";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("hello world , %s " ,  name) );
    }

    @GetMapping("/hello-world-bean/path-variable2/{differentName}")
    public HelloWorldBean helloWorldBean2(@PathVariable(value = "differentName") String name){
        return new HelloWorldBean("hello world with variable2: "+ name );
    }


}
