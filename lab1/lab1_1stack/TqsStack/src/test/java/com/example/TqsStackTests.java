package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.TqsStack;

class TqsStackTests {

    TqsStack<String> stack;

    @BeforeEach
    public void  setUp() {
        stack = new TqsStack<>();
    }

    //a
    @Test
    void testEmpty() {
        assertTrue(stack.isEmpty());
    }

    //b
    @Test
    void size() {
        assertEquals(0, stack.size());
    }

    //c
    @Test
    public void sizeAfterPush() {
        stack.push("Roberto");
        stack.push("Mariana");
        stack.push("Rafaela");
        assertEquals(3, stack.size(), "O tamanho deveria ser maior que 0, mas não é");
    }

    //d
    @Test
    public void testPushAndPop(){
        stack.push("Tiago");
        stack.push("Sara");
        assertEquals("Sara", stack.pop()); 
        
    }

    //e
    @Test
    public void fristInStack() {
        stack.push("Rafaela");
        stack.push("Matilde");
        Integer size = stack.size();
        assertEquals("Matilde", stack.peek()); 
        assertEquals(size, stack.size());
    }

    //f
    @Test
    public void emptyStack(){
        stack.push("Zé Luís");
        stack.push("Marta");

        for(int i=0; i<=stack.size(); i++){
            stack.pop();
        }
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    //g
    @Test
    public void popEmptyStack(){
       assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    //h
    @Test
    public void peekEmptyStack(){
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    //i
    @Test
    public void boundedStacks() {
        TqsStack<String> stack1 = new TqsStack<>(2);
        stack1.push("Tiago");
        stack1.push("Sara");

        assertThrows(IllegalStateException.class, () -> stack1.push("Marta"));
    }


    

}