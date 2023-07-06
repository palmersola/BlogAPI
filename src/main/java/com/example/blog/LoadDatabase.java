package com.example.blog;

import com.example.blog.blogPost.BlogPost;
import com.example.blog.blogPost.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BlogPostRepository repository){

        return args -> {
            log.info("Preloading " + repository.save(new BlogPost("First Post", "WOOHOO first post", "Palmer")));
            log.info("Preloading " + repository.save(new BlogPost("Second Post", "Dang. Second post", "Jay")));
        };

    }
}
