package sep3.sentiment.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3.sentiment.model.ChatHistory;
import sep3.sentiment.repository.ChatHistoryRepository;
import sep3.sentiment.repository.UserRepository;
import java.util.List;

@Service
public class ChatHistoryService {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatHistory createChatHistory(ChatHistory chatHistory) {
        if (chatHistory.getUserId() == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        if (!userRepository.existsById(chatHistory.getUserId())) {
            throw new IllegalArgumentException("User not found");
        }
        return chatHistoryRepository.save(chatHistory);
    }

    public List<ChatHistory> getChatHistoryByUserId(Long userId) {
        return chatHistoryRepository.findByUserId(userId);
    }

    public List<ChatHistory> getAllChatHistories() {
        return chatHistoryRepository.findAll();
    }

    public void deleteChatHistory(Long id) {
        chatHistoryRepository.deleteById(id);
    }



}
