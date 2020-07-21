import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Connections {
    private String name;
    private String number;
    private int connectionsCount;
    private List<String> connections;
}
