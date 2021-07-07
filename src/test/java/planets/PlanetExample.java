package planets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class PlanetExample {
    private Integer count;
    private String next;
    private Object previous;
    private List<Result> results;
}
