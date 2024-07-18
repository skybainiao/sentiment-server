package sep3.sentiment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sep3.sentiment.model.ChatHistory;
import java.util.List;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long>{



    List<ChatHistory> findByUserId(Long userId);

}
