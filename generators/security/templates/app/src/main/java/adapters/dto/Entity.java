package <%= packageName %>.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %>DTO {

    private Long id;

    private String text;

    private String username;

    private String email;

    private String password;
}
