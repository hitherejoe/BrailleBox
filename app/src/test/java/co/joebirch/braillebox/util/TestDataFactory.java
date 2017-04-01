package co.joebirch.braillebox.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import co.joebirch.braillebox.data.model.ArticleModel;
import co.joebirch.braillebox.data.model.NewsResponse;

public class TestDataFactory {

    private static final Random sRandom = new Random();

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static int randomInt() {
        return sRandom.nextInt(200) + Integer.SIZE - 1;
    }

    public static ArticleModel makeArticleModel() {
        ArticleModel articleModel = new ArticleModel();
        articleModel.author = randomUuid();
        articleModel.description = randomUuid();
        articleModel.title = randomUuid();
        return articleModel;
    }

    public static List<ArticleModel> makeArticleModelsList(int count) {
        List<ArticleModel> articleModels = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            articleModels.add(makeArticleModel());
        }
        return articleModels;
    }

    public static NewsResponse makeNewsResponse() {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.source = randomUuid();
        newsResponse.articles = makeArticleModelsList(4);
        return newsResponse;
    }

}