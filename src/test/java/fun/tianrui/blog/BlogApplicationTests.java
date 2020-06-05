package fun.tianrui.blog;

import fun.tianrui.blog.entity.Collection;
import fun.tianrui.blog.repository.*;
import fun.tianrui.blog.service.ArticleService;
import fun.tianrui.blog.utils.EmailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    TestRepository repository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
//    @Qualifier("articleServiceImpl")
            ArticleService articleService;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserBRepository userBRepository;
    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    EmailUtils utils;

    @Test
    void contextLoads() {
//        Article article = new Article();
//        article.setTitle("000");
//        article.setContent("000");
//        Category category = new Category();
//        category.setCategoryName("生活");
//        article.setCategories(new HashSet<Category>());
//        category.setId(4L);
//        article.getCategories().add(category);
//        categoryRepository.save(category);
//        System.out.println(articleService.findByTitle("000"));
//        System.out.println(articleService.findById(7L));

//        System.out.println(categoryRepository.findAllWithoutArticle());
//        PageRequest pageable = PageRequest.of(0, 9, Sort.Direction.DESC, "time");
//        String s = "测试";
//        System.out.println(articleRepository.findByTitleLike("%" + s + "%", pageable));
//        Optional<Comment> optional= commentRepository.findById(1L);
//        System.out.println(optional.get());
//        PageRequest pageable = PageRequest.of(2, 9, Sort.Direction.DESC, "id");
//        Page<CategoryVO> categories = categoryRepository.findAllWithoutArticleByPage(pageable);
//        System.out.println(categories.get().collect(Collectors.toList()));

//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        commentRepository.findAll(sort);
//        System.out.println();
//        articleRepository.deleteById(16L);
//        System.out.println(userBRepository.findById(1L).get());
////        System.out.println(userRepository.findById(1L).get());
//        System.out.println(commentRepository.findById(7L).get());
//        System.out.println(articleRepository.findById(20L));


    }


    @Test
    void EmailTest() {
        String Code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        utils.send("904024782@qq.com", Code);
    }

    @Test
    void ArticleTest() {
//        Integer page = -5;
//        System.out.println(articleService.getArticleListByPage(page));
//        page = 2;
//        System.out.println(articleService.getArticleListByPage(page));
//        page = 50;
//        System.out.println(articleService.getArticleListByPage(page));
//        Optional<User> optional = userRepository.findById(1L);
//        User user = optional.get();
//        user.setPassword(MD5Utils.getMd5(user.getPassword()));
//
//        userRepository.save(user);
//        User user = new User();
//        user.setId(1L);
//        Pageable pageable = PageRequest.of(1,5);
//        System.out.println(commentRepository.findByUserId(1L,pageable).get().collect(Collectors.toList()));
//        Collection collection = new Collection();
//        collection.setArticleId(20L);
//        collection.setUserId(1L);
//        System.out.println(collectionRepository.findOne(Example.of(collection)).get());

//        Collection collection = new Collection();
//        collection.setUserId(1L);
//        ArrayList<Collection> collections = new ArrayList<>();
//        collections.add(collection);
//        collection.setUserId(2L);
//        collections.add(collection);
//        System.out.println(collections.stream().map(Collection::getUserId).collect(Collectors.toList()));;

//        Collection collection = new Collection();
//        collection.setArticleId(20L);
//        collection.setUserId(1L);
//        Optional<Collection> one = collectionRepository.findOne(Example.of(collection));
//        if (one.isEmpty()){
//
//        }else {
//            collectionRepository.delete(one.get());
//
//        }
    }

    @Test
    void testFavoriteList() {
        Collection collection = new Collection();
        collection.setUserId(1L);
        int size = 20;
        PageRequest pageable = PageRequest.of(0, size, Sort.Direction.DESC, "id");
        Page<Collection> page = collectionRepository.findAll(Example.of(collection), pageable);
        System.out.println(Arrays.toString(page.get().toArray()));
    }

}
