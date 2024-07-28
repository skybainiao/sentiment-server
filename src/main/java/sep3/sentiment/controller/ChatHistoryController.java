package sep3.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sep3.sentiment.model.ChatHistory;
import sep3.sentiment.model.User;
import sep3.sentiment.service.ChatHistoryService;
import sep3.sentiment.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chathistories")
public class ChatHistoryController {

    @Autowired
    private ChatHistoryService chatHistoryService;

    @PostMapping
    public ChatHistory createChatHistory(@RequestBody ChatHistory chatHistory) {
        System.out.println("Received ChatHistory: " + chatHistory);
        if (chatHistory.getUserId() == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        return chatHistoryService.createChatHistory(chatHistory);
    }

    @GetMapping("/user/{userId}")
    public List<ChatHistory> getChatHistoryByUserId(@PathVariable Long userId) {
        return chatHistoryService.getChatHistoryByUserId(userId);
    }

    @GetMapping
    public List<ChatHistory> getAllChatHistories() {
        return chatHistoryService.getAllChatHistories();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatHistory(@PathVariable Long id) {
        chatHistoryService.deleteChatHistory(id);
        return ResponseEntity.noContent().build();
    }
}
