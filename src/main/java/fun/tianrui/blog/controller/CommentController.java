package fun.tianrui.blog.controller;

import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.entity.Comment;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }


    @PostMapping("/postComment")
    Boolean postComment(@RequestBody Comment comment) {
        Long articleId = comment.getArticleId();
        if (articleId == null) return false;
        comment.setPublic(false);
        comment.setName(comment.getUser().getUsername());
        comment.setTime(LocalDateTime.now());
        Comment result = commentRepository.save(comment);
        Article article = articleRepository.getOne(articleId);
        article.getComments().add(result);
        articleRepository.save(article);
        System.out.println(result);
        return true;
    }
}
