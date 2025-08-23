package MoEzwawi.singleton;

import MoEzwawi.factory.DefaultBibliographicFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {
    @Test
    public void testSingletonIdentity(){
        DefaultBibliographicFactory instance1 = DefaultBibliographicFactory.getInstance();
        DefaultBibliographicFactory instance2 = DefaultBibliographicFactory.getInstance();
        assertSame(instance1, instance2, "getInstance should always return the same object.");
    }
}
