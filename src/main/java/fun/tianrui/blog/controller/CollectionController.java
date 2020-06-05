package fun.tianrui.blog.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.Collection;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.repository.CollectionRepository;
import fun.tianrui.blog.vo.ArticleIdAndTitle;
import fun.tianrui.blog.vo.ArticleIdAndTitleListAndTotalPage;
import fun.tianrui.blog.vo.UserIdAndArticleIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    CollectionRepository collectionRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CollectionController(CollectionRepository collectionRepository, ArticleRepository articleRepository) {
        this.collectionRepository = collectionRepository;
        this.articleRepository = articleRepository;
    }


    @PostMapping("addFavorite")
    public Boolean addFavorite(@RequestBody UserIdAndArticleIdVO userIdAndArticleIdVO) {
        Collection collection = new Collection();
        collection.setArticleId(userIdAndArticleIdVO.getArticleId());
        collection.setUserId(userIdAndArticleIdVO.getUserId());
        try {
            collectionRepository.save(collection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取用户收藏列表
    @GetMapping("/getCollection")
    public List<Long> getCollectionByUserId(Long userId, Integer page) {
        Collection collection = new Collection();
        collection.setUserId(userId);
        Pageable pageable = PageRequest.of(--page, 20);
        return collectionRepository.findAll(Example.of(collection), pageable).stream()
                .map(Collection::getUserId).collect(Collectors.toList());
    }

    //取消收藏
    @PostMapping("cancel")
    public Boolean cancelFavorite(@RequestBody UserIdAndArticleIdVO userIdAndArticleIdVO) {
        Collection collection = new Collection();
        collection.setArticleId(userIdAndArticleIdVO.getArticleId());
        collection.setUserId(userIdAndArticleIdVO.getUserId());
        Optional<Collection> one = collectionRepository.findOne(Example.of(collection));
        if (one.isEmpty()) {
            return false;
        } else {
            collectionRepository.delete(one.get());
        }

        return true;
    }

    //判断是当前文章是否已被收藏
    @GetMapping("/isFavorite")
    public Boolean isFavorite(Long userId, Long articleId) {
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setArticleId(articleId);
        Optional<Collection> collection1 = collectionRepository.findOne(Example.of(collection));
        return collection1.isPresent();
    }

    //分页获取用户收藏文章
    @GetMapping("/getUserFavoriteByPage")
    public ArticleIdAndTitleListAndTotalPage getUserFavoriteByPage(int page, Long userId) {
        Collection collection = new Collection();
        collection.setUserId(userId);
        int size = 20;
        PageRequest pageable = PageRequest.of(--page, size, Sort.Direction.DESC, "id");
        Page<Collection> collectionPage = collectionRepository.findAll(Example.of(collection), pageable);
        List<Long> articleIdList = collectionPage.stream().map(Collection::getArticleId).collect(Collectors.toList());
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ArticleIdAndTitleListAndTotalPage articleIdAndTitleListAndTotalPage = new ArticleIdAndTitleListAndTotalPage();
        articleIdAndTitleListAndTotalPage.setArticleIdAndTitleList(
                articleRepository.findAllById(articleIdList).stream().map(e -> mapper.map(e, ArticleIdAndTitle.class)).collect(Collectors.toList())
        );
        articleIdAndTitleListAndTotalPage.setTotalPage(collectionPage.getTotalPages());
        return articleIdAndTitleListAndTotalPage;
    }
}
