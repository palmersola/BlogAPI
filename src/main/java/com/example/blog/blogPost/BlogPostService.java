package com.example.blog.blogPost;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @GetMapping
    public String listAll(Model model){
        model.addAttribute("posts", blogPostRepository.findAll());
        return"/list";
    }

    @GetMapping("/create")
    public String createGet(Model model){
        model.addAttribute("post", new BlogPost());
        return "/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") BlogPost post){
        blogPostRepository.save(post);
        return "redirect: ../../";

    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable("id") int id, Model model){
        BlogPost post = blogPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));
        model.addAttribute("post", post);
        return "/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable("id") int id, @ModelAttribute("post") BlogPost post){
        post.setId(id);
        blogPostRepository.save(post);
        return "redirect: ../../../";
    }

    @GetMapping("/{id}/delete")
    public String deleteGet(@PathVariable("id") int id){
        blogPostRepository.deleteById(id);
        return "redirect: ../../../";
    }
}
