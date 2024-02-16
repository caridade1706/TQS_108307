package com.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    
    private LinkedList<T> collection;
    private int limit_stack;

    public TqsStack() {
        collection = new LinkedList<T>();   
    }

    public TqsStack(int limit) {
        limit_stack = limit;
        collection = new LinkedList<T>();
    }

    public T pop() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException("A pilha está vazia");
        } else {
            return collection.removeFirst();
        }
        
    }

    public int size() {
        return collection.size();
    }

    public T peek() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException("A pilha está vazia");
        } else {
            return collection.getFirst();
        }
    }
    
    public void push(T element) {
        if (collection.size()>0 && collection.size() == limit_stack){
            throw new IllegalStateException("A pilha está cheia");
        } else {
            collection.addFirst(element);
        }        
    }
   
    public boolean isEmpty() {
        return collection.isEmpty();
    }
}