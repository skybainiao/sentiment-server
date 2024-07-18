package sep3.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep3.sentiment.model.ChatHistory;
import sep3.sentiment.repository.ChatHistoryRepository;

import java.util.List;

@Service
public class ChatHistoryService {

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    public ChatHistory createChatHistory(ChatHistory chatHistory) {
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
