package sep3.sentiment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sep3.sentiment.model.ChatHistory;
import sep3.sentiment.repository.ChatHistoryRepository;
import sep3.sentiment.repository.UserRepository;
import sep3.sentiment.service.ChatHistoryService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ChatHistoryServiceTest {

    @Mock
    private ChatHistoryRepository chatHistoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ChatHistoryService chatHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChatHistorySuccess() {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setId(1L);
        chatHistory.setUserMessage("Hello");
        chatHistory.setBotMessage("Hi");
        chatHistory.setTimestamp(LocalDateTime.now());
        chatHistory.setUserId(1L);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(chatHistoryRepository.save(any(ChatHistory.class))).thenReturn(chatHistory);

        ChatHistory createdChatHistory = chatHistoryService.createChatHistory(chatHistory);

        assertNotNull(createdChatHistory);
        assertEquals(1L, createdChatHistory.getId());
        assertEquals("Hello", createdChatHistory.getUserMessage());
        assertEquals("Hi", createdChatHistory.getBotMessage());
        verify(userRepository, times(1)).existsById(1L);
        verify(chatHistoryRepository, times(1)).save(chatHistory);
    }

    @Test
    void testGetChatHistoryByUserId() {
        ChatHistory chatHistory1 = new ChatHistory();
        chatHistory1.setId(1L);
        chatHistory1.setUserId(1L);

        ChatHistory chatHistory2 = new ChatHistory();
        chatHistory2.setId(2L);
        chatHistory2.setUserId(1L);

        when(chatHistoryRepository.findByUserId(1L)).thenReturn(Arrays.asList(chatHistory1, chatHistory2));

        List<ChatHistory> chatHistories = chatHistoryService.getChatHistoryByUserId(1L);

        assertNotNull(chatHistories);
        assertEquals(2, chatHistories.size());
        assertEquals(1L, chatHistories.get(0).getUserId());
        assertEquals(1L, chatHistories.get(1).getUserId());
        verify(chatHistoryRepository, times(1)).findByUserId(1L);
    }
}
