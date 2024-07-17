package sep3.sentiment.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatHistoryDTO {

    private Long id;
    private String message;
    private LocalDateTime timestamp;

}
