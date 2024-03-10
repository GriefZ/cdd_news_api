package zim.grigory.cdd_news_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zim.grigory.cdd_news_api.exception.ResourceNotFoundException;
import zim.grigory.cdd_news_api.models.NewsItem;
import zim.grigory.cdd_news_api.models.NewsItemDTO;
import zim.grigory.cdd_news_api.models.NewsType;
import zim.grigory.cdd_news_api.repositories.NewsItemRepository;
import zim.grigory.cdd_news_api.repositories.NewsTypeRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsItemController {
    @Autowired
    public NewsItemRepository newsItemRepository;
    @Autowired
    public NewsTypeRepository newsTypeRepository;
    @PostMapping("/news")
    public ResponseEntity<?> create(@RequestBody NewsItemDTO newsItemDTO) {
        NewsItem newsItem = newsItemFromDto(newsItemDTO);
        newsItemRepository.save(newsItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/news")
    public  ResponseEntity<List<NewsItem>> read() {
        final List<NewsItem> newsItems = newsItemRepository.findAll();
        return newsItems != null
                ? new ResponseEntity<>(newsItems, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/newstypes/{newsTypeId}/news")
    ResponseEntity<List<NewsItem>> getAllNewsItemsByNewsTypeId(@PathVariable(value = "newsTypeId") Long newsTypeId) {
        NewsType newsType = newsTypeRepository.findById(newsTypeId)
                .orElseThrow(()->new ResourceNotFoundException("Not found news type with id = " + newsTypeId));
        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.addAll(newsType.getNewsItemSet());
        return new ResponseEntity<>(newsItems, HttpStatus.OK);
    }

    @GetMapping(value = "/news/{id}")
    ResponseEntity<NewsItem> getNewsItemById(@PathVariable(name = "id") Long newsItemId) {
        NewsItem newsItem = newsItemRepository.findById(newsItemId)
                .orElseThrow(() -> new ResourceNotFoundException("News item with id = " + newsItemId + ", not found"));
        return new ResponseEntity<>(newsItem, HttpStatus.OK);
    }

    @PutMapping(value = "/news/{id}") //Подумать над Patch вместо Put
    public ResponseEntity<?> updateNewsItem(@PathVariable(name = "id") Long newsItemId, @RequestBody NewsItem newsItem) {
        NewsItem existedNewsItem = newsItemRepository.findById(newsItemId)
                .orElseThrow(() -> new ResourceNotFoundException("News item with id = " + newsItemId + ", not found"));
        existedNewsItem.setTitle(newsItem.getTitle());
        existedNewsItem.setDescription(newsItem.getDescription());
        existedNewsItem.setText(newsItem.getText());
        existedNewsItem.setType(newsItem.getType());
        return new ResponseEntity<>(newsItemRepository.save(existedNewsItem), HttpStatus.OK);
    }

    @DeleteMapping(value = "/news/{id}")
    public ResponseEntity<?> deleteNewsItem(@PathVariable(name = "id") Long newsItemId) {//Можно добавить проверку на наличие
        newsItemRepository.deleteById(newsItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private NewsItem newsItemFromDto(NewsItemDTO dto) {
        NewsType newsType = newsTypeRepository.findById(dto.getTypeId())
                .orElseThrow(()->new ResourceNotFoundException("Not found news type with id = " + dto.getTypeId()));
        NewsItem newsItem = new NewsItem();
        newsItem.setType(newsType);
        newsItem.setId(dto.getTypeId());
        newsItem.setTitle(dto.getTitle());
        newsItem.setDescription(dto.getDescription());
        newsItem.setText(dto.getText());
        return newsItem;
    }

    private NewsItemDTO dtoFromNewsItem(NewsItem newsItem)
    {
        NewsItemDTO dto = new NewsItemDTO();
        dto.setId(newsItem.getId());
        dto.setTitle(newsItem.getTitle());
        dto.setDescription(newsItem.getDescription());
        dto.setText(newsItem.getText());
        dto.setTypeId(newsItem.getType().getId());
        return dto;
    }
}
