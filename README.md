# Undo-Redo Application 
## Implementation using Generic Dynamic Stack-Array 
### Interface Reference :
```java
public interface StackFunctions<T> {
    void push(T number);
    T pop();
    boolean isEmpty();
    void display();
}
```
### Code Reference for Dynamic Stack :
```java
class MyStack<T> implements StackFunctions<T> {
    T[] array;
    int top;
    Class<T> newClass;
    int size;

    @SuppressWarnings("unchecked")
    MyStack(Class<T> tush) {
        this.size = 1;
        this.newClass = tush;
        this.array = (T[]) Array.newInstance(tush,this.size);
        this.top=0;
    }

    @Override
    public void push(T value) {
        if(top>=0){
            array = resize();
        }
        this.array[this.top++]=value;
    }

    @SuppressWarnings("unchecked")
    T[]  resize(){
        T[] newArray = (T[]) Array.newInstance(newClass,this.size++);
        System.arraycopy(array,0,newArray,0,array.length);
        return newArray;
    }

    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if(!isEmpty()) {
            T object = array[-1+top--];
            T[] newArray = (T[]) Array.newInstance(newClass,--this.size-1);
            System.arraycopy(array,0,newArray,0,newArray.length);
            array = newArray;
            return object;
        }
        else {
            this.top = 0;
            throw new EmptyStackException();
        }
    }

    @Override
    public void display() {
        for (T e : array){
            System.out.println(e);
        }
    }

}
```
### Libraries Imported :
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.EmptyStackException;
```
## Description :
This program works on Java swing class for GUI reference
## Screenshots :

 