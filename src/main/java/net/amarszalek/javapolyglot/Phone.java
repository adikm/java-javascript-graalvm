package net.amarszalek.javapolyglot;


public class Phone {

    public final int number;

    public Phone(int number) {
        this.number = number;
    }

    public void call(String name) {
        System.out.println("Calling...: " + name);
    }

}
