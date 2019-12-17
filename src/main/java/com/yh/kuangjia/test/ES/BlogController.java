package com.yh.kuangjia.test.ES;

import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.test.ES.Item;
import com.yh.kuangjia.test.ES.ItemRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/blog")
@Api("blog接口")
public class BlogController {
    @Autowired
    private ItemRepository blogRepository;

    @IgnoreLogin
    @PostMapping("/add")
    public Result add(@RequestBody Item blogModel) {
        blogRepository.save(blogModel);
        return Result.success();
    }

    @IgnoreLogin
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable String id) {
        Optional<Item> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            Item blogModel = blogModelOptional.get();
            return Result.success(blogModel);
        }
        return new Result(1,"error");
    }
}