package PaooGame;
import java.util.HashMap;
import java.util.Map;

public class CollisionStrategyRegistry {
    private static final Map<Integer, CollisionStrategy> strategies = new HashMap<>();
    private static final Map<Class<?>, CollisionStrategy> characterStrategies = new HashMap<>();

    public CollisionStrategyRegistry() {
        System.out.println("s a facut legatura intre tile si coliziune ");
    }

    public static void registerStrategy(int tileId, CollisionStrategy strategy) {
        strategies.put(tileId, strategy);
    }

    public static CollisionStrategy getStrategy(int tileId) {
        return strategies.get(tileId);
    }

    public static void registerCharacterStrategy(Class<?> clasa, CollisionStrategy strategy)
    {
        characterStrategies.put(clasa, strategy);
    }

    public static CollisionStrategy getCharacterStrategy(Class<?> clasa)
    {
        return characterStrategies.get(clasa);
    }
}