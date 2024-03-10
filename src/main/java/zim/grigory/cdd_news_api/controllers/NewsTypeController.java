package zim.grigory.cdd_news_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zim.grigory.cdd_news_api.exception.ResourceNotFoundException;
import zim.grigory.cdd_news_api.models.NewsType;
import zim.grigory.cdd_news_api.repositories.NewsTypeRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class NewsTypeController {
    @Autowired
    NewsTypeRepository newsTypeRepository;

    @GetMapping("/newstypes")
    public ResponseEntity<List<NewsType>> getAllNewsTypes() {
        List<NewsType> newsTypes = newsTypeRepository.findAll();
        return !newsTypes.isEmpty()
                ? new ResponseEntity<>(newsTypes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/newstypes/{id}")
    public ResponseEntity<NewsType> getNewsTypeById(@PathVariable(name = "id") Long newsTypeId) {
        NewsType newsType = newsTypeRepository.findById(newsTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found news type with id = " + newsTypeId));
        return new ResponseEntity<>(newsType, HttpStatus.OK);
    }

    @PostMapping("/newstypes")
    public ResponseEntity<NewsType> createNewsType(@RequestBody NewsType newsType) {
        NewsType savedNewsType = newsTypeRepository.save(new NewsType(newsType.getName(), newsType.getColor()));
        return new ResponseEntity<>(savedNewsType, HttpStatus.CREATED);
    }

    @PutMapping(value = "/newstypes/{id}") //Подумать над Patch вместо Put
    public ResponseEntity<?> updateNewsType(@PathVariable(name = "id") Long newsTypeId, @RequestBody NewsType newsType) {
        NewsType existedNewsType = newsTypeRepository.findById(newsTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found news type with id = " + newsTypeId));
        existedNewsType.setName(newsType.getName());
        existedNewsType.setColor(newsType.getColor());
        return new ResponseEntity<>(newsTypeRepository.save(existedNewsType), HttpStatus.OK);
    }
    @DeleteMapping(value = "/newstypes/{id}")
    public ResponseEntity<?> deleteNewsType(@PathVariable(name = "id") Long newsTypeId) {
        newsTypeRepository.deleteById(newsTypeId);
        return new ResponseEntity<>("News Type successfully deleted",HttpStatus.OK);
    }
}
