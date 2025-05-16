package PaooGame;
import java.util.HashMap;
import java.util.Map;

public class CollisionStrategyRegistry {
    private static final Map<Integer, CollisionStrategy> strategies = new HashMap<>();

    public CollisionStrategyRegistry() {
        System.out.println("s a facut legatura intre tile si coliziune ");
    }

    public static void registerStrategy(int tileId, CollisionStrategy strategy) {
        strategies.put(tileId, strategy);
    }

    public static CollisionStrategy getStrategy(int tileId) {
        return strategies.get(tileId);
    }
}