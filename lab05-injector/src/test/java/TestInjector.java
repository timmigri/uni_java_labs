import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Assertions;

import labs.grishchenko.SomeBean;
import labs.grishchenko.InjectionException;
import labs.grishchenko.Injector;

public class TestInjector {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
	public void test_injectionFirstExample() {
        try {
            SomeBean someBean = (new Injector("example_first.properties")).inject(new SomeBean());
            someBean.foo();
            Assertions.assertEquals("AC", systemOutRule.getLog());
        } catch (InjectionException e) {
            Assertions.fail(e.getMessage());
        }
	}

    @Test
	public void test_injectionSecondExample() {
        try {
            SomeBean someBean = (new Injector("example_second.properties")).inject(new SomeBean());
            someBean.foo();
            Assertions.assertEquals("BC", systemOutRule.getLog());
        } catch (InjectionException e) {
            Assertions.fail(e.getMessage());
        }
	}

    @Test
	public void test_injectionInvalidFile() {
        Assertions.assertThrows(InjectionException.class, () -> {
            SomeBean someBean = (new Injector("not_found.properties")).inject(new SomeBean());
            someBean.foo();
        });
	}

}
