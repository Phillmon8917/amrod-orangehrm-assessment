package automation.basepagelifecycle;

/**
 * This interface serves as a template for base tests.
 * Each base test needs to have init method else the child will be forced to have it
 * Init is an abstract method which is used to initialize elements during runtime
 */
@FunctionalInterface
public interface BasePageLifeCycle
{
    void init();
}
