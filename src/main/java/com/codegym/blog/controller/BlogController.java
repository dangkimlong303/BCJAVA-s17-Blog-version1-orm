package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        Blog blog = new Blog();
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView saveBlog(@ModelAttribute("blogObj") Blog blog){
        blogService.save(blog);
        Blog blog1 = new Blog();
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog",blog1);
        modelAndView.addObject("mes","new blog created successfully");
        return modelAndView;
    }
    @GetMapping("/blogs")
    public ModelAndView showList(){
        List<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("edit")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("mes","Blog update successfully");
        return modelAndView;
    }

    @GetMapping("delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }
    @PostMapping("delete")
    public ModelAndView deleteBlog(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("mess","Blog delete successfully");
        return modelAndView;
    }
}
