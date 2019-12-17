package route;

import java.util.Set;

public class Intersection {

    private final Set<Line> lines;

    public Intersection(Set<Line> lines) {
        this.lines = lines;
    }

    public Set<Line> getConnections() {
        return this.lines;
    }

}
