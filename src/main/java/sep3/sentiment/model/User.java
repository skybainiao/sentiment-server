package sep3.sentiment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ChatHistory> chatHistories;

    public void setChatHistories(List<ChatHistory> chatHistories) {
        this.chatHistories = chatHistories;
    }

    public List<ChatHistory> getChatHistories() {
        return chatHistories;
    }

}