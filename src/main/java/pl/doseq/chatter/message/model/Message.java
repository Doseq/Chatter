package pl.doseq.chatter.message.model;

import lombok.*;
import pl.doseq.chatter.user.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private long id;
    @NotBlank
    private String content;
    @OneToOne
    private User user;
    private LocalDateTime createdAt = LocalDateTime.now();

}
