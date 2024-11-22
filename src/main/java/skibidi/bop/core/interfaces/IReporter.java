package skibidi.bop.core.interfaces;

public interface IReporter<T> {

    void createTest(T reporter);

    void pass(T reporter);

    void fail(T reporter);

    void skip(T reporter);
}
