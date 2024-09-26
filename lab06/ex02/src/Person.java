package ex02.src;
public class Person {
    private String nome;
    private int age;

    /**
     * Constructor for Person
     * @param nome
     * @param age
     */
    private Person(String nome,int age) {
        this.nome = nome;
        this.age = age;
    }

    /**
     * Create a new Person
     * @param nome
     * @param age
     * @return Person
     */
    public static Person createPerson(String nome, int age) {
        if (age == 0 || nome == null) {
            return new Person("Unknown", 0);
        }
        return new Person(nome, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "nome='" + nome + '\'' +
                ", age=" + age +
                '}';
    }
}
