package pl.doseq.chatter.message.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.doseq.chatter.user.model.User;

@NoArgsConstructor
@Setter
@Getter
public class NewMessageDto {

    private String content;
    private User user;

}
