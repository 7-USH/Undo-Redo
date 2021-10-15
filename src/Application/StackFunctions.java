package Application;

public interface StackFunctions<T> {
    void push(T number);
    T pop();
    boolean isEmpty();
    void display();
}
