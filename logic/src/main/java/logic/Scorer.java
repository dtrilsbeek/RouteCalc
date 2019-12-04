package logic;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}