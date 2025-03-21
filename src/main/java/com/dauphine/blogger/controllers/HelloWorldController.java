package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController

@Tag(
        name = "Hello world API",
        description = "My first hello world endpoints"
)

public class HelloWorldController {

    @GetMapping("hello-world") // http://localhost:1234/hello-world
    @Operation(
            summary = "Hello World endpoint",
            description = "Returns 'Hello World !' "
    )
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello") // http://localhost:1234/hello?name=christina
    @Operation(
            summary = "Hello by name (parameter) endpoint",
            description = "Returns 'Hello {name} !'  by parameter"
    )
    public String helloByName(
            @Parameter(description = "Name to greet")
            @RequestParam String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("hello/{name}") // http://localhost:1234/hello/christina
    @Operation(
            summary = "Hello by name (path variable) endpoint",
            description = "Returns 'Hello {name} !' by path variable"
    )
    public String hello(
            @Parameter(description = "Name to greet")
            @PathVariable String name) {
        return "Hello " + name + "!";
    }

}
