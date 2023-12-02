package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Post;
import com.example.demo.repository.PostRepository;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;
    
     @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "blog";
    }

     @GetMapping("/blog/add")
     public String add(Model model){

        return "blog-add";
     }


     @PostMapping("/blog/add")
     public String blogPostAdd(@RequestParam String title, @RequestParam String announce,@RequestParam String text,Model model){

        Post post = new Post(title,announce,text);

        postRepository.save(post);
        return "redirect:/blog";

     }

     @GetMapping("blog/{id}")
     public String getById(@PathVariable(value = "id") long id,Model model){

        if (!postRepository.existsById(id)) {
            return "redirect:/blog";

        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);

       model.addAttribute("post",res);
        
        return "blog-details";
     }

     @GetMapping("blog/{id}/update")
     public String update(@PathVariable(value = "id") long id, Model model){

         if (!postRepository.existsById(id)) {
            return "redirect:/blog";

        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);

         model.addAttribute("post",res);
        
        return "blog-update";      
     }


        @PostMapping("blog/{id}/update")
        public String update(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String announce,@RequestParam String text,Model model)
        {

         Post post = postRepository.findById(id).orElseThrow();

        post.SetTitle(title);
        post.SetAnnounce(announce);
        post.SetText(text);

         postRepository.save(post);
        
        return "redirect:/blog";      
     }

      @PostMapping("blog/{id}/remove")
     public String removeItem(@PathVariable(value = "id") long id, Model model){


        Post post = postRepository.findById(id).orElseThrow();

        postRepository.delete(post);


        
        return "redirect:/blog";      
     }








}
