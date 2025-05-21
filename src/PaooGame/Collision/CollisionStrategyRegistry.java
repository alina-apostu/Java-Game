package PaooGame.Collision;


import java.util.HashMap;
import java.util.Map;

// registru care retine o asociere intre id-ul unui tile si o strategie de coliziune si respectiv intre tipul unei clase de Character si o strategie de coliziune

public class CollisionStrategyRegistry
{
    private static final Map<Integer, CollisionStrategy> strategies = new HashMap<>();
    private static final Map<Class<?>, CollisionStrategy> characterStrategies = new HashMap<>();

    public CollisionStrategyRegistry()
    {
        System.out.println("s a facut coliziune ");
    }

    public static void registerStrategy(int tileId, CollisionStrategy strategy)
    {
        strategies.put(tileId, strategy);
    }

    public static CollisionStrategy getStrategy(int tileId)
    {
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