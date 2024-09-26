package ex02.src;
import java.util.List;

public class MovieMain {
    public static void main(String[] args) {
        Movie movie = new Movie.Builder("The Godfather", 1972)
                .setDirector(Person.createPerson("Francis Ford Coppola", 81))
                .setWriter(Person.createPerson("Mario Puzo", 78))
                .setSeries("The Godfather Trilogy")
                .setCast(List.of(
                        Person.createPerson("Marlon Brando", 80),
                        Person.createPerson("Al Pacino", 81),
                        Person.createPerson("James Caan", 81),
                        Person.createPerson("Robert Duvall", 90),
                        Person.createPerson("Diane Keaton", 75)
                ))
                .setLocations(List.of(
                        Place.createPlace("Paramount Studios", "Los Angeles", "USA"),
                        Place.createPlace("Savoy Hotel", "London", "UK"),
                        Place.createPlace("Sicily", "Corleone", "Italy")
                ))
                .setLanguages(List.of("English", "Italian", "Latin"))
                .setGenres(List.of("Crime", "Drama"))
                .setIsTelevision(false)
                .setIsNetflix(true)
                .setIsIndependent(false)
                .build();

        System.out.println(movie);
    }
}
