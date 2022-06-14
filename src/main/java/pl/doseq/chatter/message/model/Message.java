package pl.doseq.chatter.message.model;

import lombok.*;
import pl.doseq.chatter.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String content;
    @OneToOne
    private User user;
    private LocalDateTime createdAt = LocalDateTime.now();

}
