package zim.grigory.cdd_news_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "newstypes")
public class NewsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newstype_id")
    private Long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<NewsItem> newsItemSet = new HashSet<NewsItem>();

    public NewsType(){}
    public NewsType(String name, String color) {
        this.name = name;
        this.color = color;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<NewsItem> getNewsItemSet() {
        return newsItemSet;
    }

    public void setNewsItemSet(Set<NewsItem> newsItemSet) {
        this.newsItemSet = newsItemSet;
    }
}
