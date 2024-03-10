package zim.grigory.cdd_news_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zim.grigory.cdd_news_api.models.NewsType;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
}
