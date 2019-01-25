package reflect;

public class Person {

    private int age;
    private String name;

    public Person() {

    }

    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean testInvoke(Integer age, String name) {
        System.out.println("得到参数age:" + age + "   name:" + name);
        return true;
    }


}
