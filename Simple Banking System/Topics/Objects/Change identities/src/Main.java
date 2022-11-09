class Person {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}



class MakingChanges {
    public static void changeIdentities(Person p1, Person p2) {
        // write your code here
        int tempAge = p1.getAge();
        String tempName = p1.getName();

        p1.setName(p2.getName());
        p1.setAge(p2.getAge());

        p2.setAge(tempAge);
        p2.setName(tempName);
    }




}