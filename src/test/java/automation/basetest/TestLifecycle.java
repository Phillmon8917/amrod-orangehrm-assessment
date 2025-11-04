package automation.basetest;

/**
 * Interface determines test life cycle,
 * it's a blueprint for creating any test
 */
public interface TestLifecycle
{
    void initializeDriver();

    void initializeReporting();

    void cleanup();
}
