package PaooGame;
import java.util.HashMap;
import java.util.Map;

public class CollisionStrategyRegistry {
    private static final Map<Integer, CollisionStrategy> strategies = new HashMap<>();

    public static void registerStrategy(int tileId, CollisionStrategy strategy) {
        strategies.put(tileId, strategy);
    }

    public static CollisionStrategy getStrategy(int tileId) {
        return strategies.get(tileId);
    }
}