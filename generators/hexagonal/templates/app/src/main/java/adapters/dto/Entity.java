package <%= packageName %>.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %>DTO {

    private Long id;

    private String text;
}
