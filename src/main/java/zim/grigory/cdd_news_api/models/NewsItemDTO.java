package zim.grigory.cdd_news_api.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


/*Возможно это не нужно и можно все сделать дерективами*/
public class NewsItemDTO {
    private Long id;
    private String title;
    private String description;
    private String text;

    private Long typeId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
