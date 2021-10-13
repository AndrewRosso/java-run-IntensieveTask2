package ru.tuanviet.javabox;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class App {
    private final String url;
    private final int countNews;

    public App(String url, int countNews) {
        this.url = url;
        this.countNews = countNews;
    }

    public static void main(String[] args) {
        List<String> formattedList = new App(
                "https://hacker-news.firebaseio.com/v0/topstories.json",
                10)
                .getFormattedNewsFromTopNews();

        for (String news : formattedList) {
            System.out.println(news);
        }
    }

    public List<String> getFormattedNewsFromTopNews() {
        List<String> listIds = getTopNewsId();
        List<NewsModel> newsModels = getNewsFromIds(listIds);
        NewsService newsService = new NewsService(newsModels);
        return newsService.formNewsOutput();
    }

    public List<String> getTopNewsId() {
        HttpClient client = new HttpClient();
        List<String> ids = client.fetch(url, new TypeReference<List<String>>(){});
        System.out.println(ids.toString());
        return ids;
    }

    public List<NewsModel> getNewsFromIds(List<String> ids) {
        HttpClient client = new HttpClient();
        List<NewsModel> news = new ArrayList<>(countNews);
        final String startUrl = "https://hacker-news.firebaseio.com/v0/item/";
        final String endUrl = ".json";
        for (int i = 0; i < countNews; i++) {
            String url = startUrl + ids.get(i) + endUrl;
            news.add(client.fetch(url, NewsModel.class));
            formUrl(news.get(i), url);
        }
        return news;
    }

    public void formUrl(NewsModel model, String url) {
        if (model.getUrl() == null) {
            model.setUrl(url);
        }
    }

}
