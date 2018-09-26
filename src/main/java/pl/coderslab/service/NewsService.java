package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.News;
import pl.coderslab.repository.NewsRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public String[] save(News news) {
        String[] message = new String[2];
        news.setDate(LocalDate.now());
        String newsName = news.getName();
        try {
            newsRepository.save(news);
            message[0] = "message";
            message[1] = "Wiadomość " + newsName + " dodana prawidłowo.";
        } catch (Exception e) {
            message[0] = "messageDanger";
            message[1] = "Wiadomość " + newsName + " nie została dodana.";
        }
        return message;
    }
}