package fun.tianrui.blog.controller;

import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.entity.Comment;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.repository.CommentRepository;
import fun.tianrui.blog.vo.CommentListAndTotalPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * @param page 要获取的页数
     * @return 该页的评论list和总页数
     */
    @GetMapping("/getAllComment")
    CommentListAndTotalPage getAllCommentByPage(Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "time");
        PageRequest pageRequest = PageRequest.of(--page, 20, sort);
        Page<Comment> commentPage = commentRepository.findAll(pageRequest);
        List<Comment> commentList = commentPage.toList().stream().peek(e -> e.getUser().setPassword("")).collect(Collectors.toList());
        CommentListAndTotalPage commentListAndTotalPage = new CommentListAndTotalPage();
        commentListAndTotalPage.setComments(commentList);
        commentListAndTotalPage.setTotalPage(commentPage.getTotalPages());
        return commentListAndTotalPage;
    }


    @PostMapping("/deleteCommentByIds")
    public boolean deleteCommentByIds(@RequestBody List<Long> ids) {
//        articleRepository.deleteById(1L);
        try {
            ids.forEach(commentRepository::deleteById);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/deleteCommentById")
    public boolean deleteCommentById(Long id) {
//        articleRepository.deleteById(1L);
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/publicComment")
    public boolean publicComment(@RequestBody List<Long> ids) {
        try {
            List<Comment> comments = new ArrayList<>();
            ids.forEach(e -> {
                Comment comment = commentRepository.findById(e).orElse(null);
                if (comment != null) {
                    comment.setPublic(true);
                    comments.add(comment);
                }
            });
            if (comments.size() == 0) return false;
            commentRepository.saveAll(comments);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
