package MoEzwawi.singleton;

import MoEzwawi.factory.DefaultBibliographicFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {
    @Test
    public void testSingletonIdentity(){
        DefaultBibliographicFactory instance1 = DefaultBibliographicFactory.getFactory();
        DefaultBibliographicFactory instance2 = DefaultBibliographicFactory.getFactory();
        assertSame(instance1, instance2, "getFactory should always return the same object.");
    }
}
