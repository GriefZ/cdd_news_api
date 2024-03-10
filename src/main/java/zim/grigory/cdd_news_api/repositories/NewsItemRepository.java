package zim.grigory.cdd_news_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zim.grigory.cdd_news_api.models.NewsItem;

@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {
}
