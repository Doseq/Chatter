package pl.doseq.chatter.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class NewChannelDto {

    @NotBlank
    private String name;

}
