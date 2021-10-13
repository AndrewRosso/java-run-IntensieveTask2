package ru.tuanviet.javabox;

import java.util.ArrayList;
import java.util.List;

public class NewsService {

    private List<NewsModel> news;

    public NewsService(List<NewsModel> news) {
        this.news = news;
    }

    public List<String> formNewsOutput() {
        String res;
        List<String> formattedNews = new ArrayList<>();
        for (NewsModel newsModel : news) {
            res = String.format("%s (%s points) \n%s\n"
                    , newsModel.getTitle()
                    , newsModel.getScore()
                    , newsModel.getUrl()
            );
            formattedNews.add(res);
        }
        return formattedNews;
    }
}
