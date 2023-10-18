import labs.grishchenko.Container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestContainer {
    @Test
    public void test_insertToContainer() {
        Container<String> fruits = new Container<>();
        Assertions.assertEquals(fruits.size(), 0);

        fruits.insert(TestFruit.mango);
        Assertions.assertEquals(fruits.size(), 1);

        fruits.insert(TestFruit.banana);
        Assertions.assertEquals(fruits.size(), 2);
    }

    @Test
    public void test_removeFromContainer() {
        Container<String> fruits = new Container<>();
        fruits.insert(TestFruit.mango);
        fruits.insert(TestFruit.banana);
        fruits.insert(TestFruit.mango);

        Assertions.assertTrue(fruits.remove(TestFruit.mango));
        Assertions.assertEquals(fruits.size(), 2);

        Assertions.assertTrue(fruits.remove(TestFruit.mango));
        Assertions.assertEquals(fruits.size(), 1);

        Assertions.assertFalse(fruits.remove(TestFruit.mango));
        Assertions.assertEquals(fruits.size(), 1);
    }

    @Test
    public void test_getElementByIndexInContainer() {
        Container<String> fruits = new Container<>();
        fruits.insert(TestFruit.banana);
        fruits.insert(TestFruit.mango);

        Assertions.assertEquals(fruits.get(0), TestFruit.banana);
        Assertions.assertEquals(fruits.get(1), TestFruit.mango);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            fruits.get(2);
        });
    }
}

class TestFruit {
    static String mango = "Mango";
    static String banana = "Banana";
}