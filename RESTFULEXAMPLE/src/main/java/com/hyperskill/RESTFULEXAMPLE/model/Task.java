package com.hyperskill.RESTFULEXAMPLE.model;

public class Task {
    private int id;
    private String name;
    private String description;
    private boolean completed;

    public Task(int id, String name, String description, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    class A {

        protected int a;
    }

    class B extends A {

        protected int b;

        public B(int a, int b) {
            super();     // 1
            super.a = a; // 2
            super.b = b; // 3
        }
    }

    class C extends B {

        protected int c;

        public C(int a, int b) {
            super(a); // 4
        }

        public C(int a) {
            super(a, 10); // 5
        }
    }
    class Animal {
        private String name;

        Animal(String name) {
            assert (name != null) : "Name must not be null";
            this.name = name;
        }
    }

}
