package ex02.src;
import java.util.List;
import java.util.ArrayList;

public class Movie {
   private final String title;
   private final int year;
   private final Person director;
   private final Person writer;
   private final String series;
   private final List<Person> cast;
   private final List<Place> locations;
   private final List<String> languages;
   private final List<String> genres;
   private final boolean isTelevision;
   private final boolean isNetflix;
   private final boolean isIndependent;

   /**
    * Constructor for Movie
    * @param builder
    */
   protected Movie(Builder builder) {
      this.title = builder.title;
      this.year = builder.year;
      this.director = builder.director;
      this.writer = builder.writer;
      this.series = builder.series;
      this.cast = builder.cast;
      this.locations = builder.locations;
      this.languages = builder.languages;
      this.genres = builder.genres;
      this.isTelevision = builder.isTelevision;
      this.isNetflix = builder.isNetflix;
      this.isIndependent = builder.isIndependent;
   }

   /**
    * ToString method for Movie
   */
   @Override
   public String toString() {
      return "Movie {" +
              "title='" + title + '\'' + "\n" +
              ", year=" + year + "\n" +
              ", director=" + director + "\n" +
              ", writer=" + writer + "\n" +
              ", series='" + series + '\'' + "\n" +
              ", cast=" + cast + "\n" +
              ", locations=" + locations + "\n" +
              ", languages=" + languages + "\n" +
              ", genres=" + genres + "\n" +
              ", isTelevision=" + isTelevision + "\n" +
              ", isNetflix=" + isNetflix + "\n" +
              ", isIndependent=" + isIndependent + "\n" +
              '}';
   }

   public static class Builder {
      private final String title;
      private final int year;
      private Person director = Person.createPerson(null, 0);
      private Person writer = Person.createPerson(null, 0);
      private String series = "";
      private List<Person> cast = new ArrayList<Person>();
      private List<Place> locations = new ArrayList<Place>();
      private List<String> languages = new ArrayList<String>();
      private List<String> genres = new ArrayList<String>();
      private boolean isTelevision = false;
      private boolean isNetflix = false;
      private boolean isIndependent = false;

      /**
       * Constructor for Builder
       * @param title
       * @param year
       */
      public Builder(String title, int year) {
         this.title = title;
         this.year = year;

      }

      /**
       * Set director for Movie
       * @param director
       * @return Builder
       */
      public Builder setDirector(Person director) {
         this.director = director;
         return this;
      }

      /**
       * Set writer for Movie
       * @param writer
       * @return Builder
       */
      public Builder setWriter(Person writer) {
         this.writer = writer;
         return this;
      }

      /**
       * Set series for Movie
       * @param series
       * @return Builder
       */
      public Builder setSeries(String series) {
         this.series = series;
         return this;
      }

      /**
       * Set cast for Movie
       * @param cast
       * @return Builder
       */
      public Builder setCast(List<Person> cast) {
         this.cast = cast;
         return this;
      }

      /**
       * Set locations for Movie
       * @param locations
       * @return Builder
       */
      public Builder setLocations(List<Place> locations) {
         this.locations = locations;
         return this;
      }

      /**
       * Set languages for Movie
       * @param languages
       * @return Builder
       */
      public Builder setLanguages(List<String> languages) {
         this.languages = languages;
         return this;
      }

      /**
       * Set genres for Movie
       * @param genres
       * @return Builder
       */
      public Builder setGenres(List<String> genres) {
         this.genres = genres;
         return this;
      }

      /**
       * Set isTelevision for Movie
       * @param isTelevision
       * @return Builder
       */
      public Builder setIsTelevision(boolean isTelevision) {
         this.isTelevision = isTelevision;
         return this;
      }

      /**
       * Set isNetflix for Movie
       * @param isNetflix
       * @return Builder
       */
      public Builder setIsNetflix(boolean isNetflix) {
         this.isNetflix = isNetflix;
         return this;
      }

      /**
       * Set isIndependent for Movie
       * @param isIndependent
       * @return Builder
       */
      public Builder setIsIndependent(boolean isIndependent) {
         this.isIndependent = isIndependent;
         return this;
      }

      /**
       * Reset all fields
       */
      public void reset() {
         this.director = Person.createPerson(null, 0);
         this.writer = Person.createPerson(null, 0);
         this.series = null;
         this.cast = new ArrayList<Person>();
         this.locations = new ArrayList<Place>();
         this.languages = new ArrayList<String>();
         this.genres = new ArrayList<String>();
         this.isTelevision = false;
         this.isNetflix = false;
         this.isIndependent = false;
      }

      /**
       * Build Movie
       * @return Movie
       */      
      public Movie build() {
         if (title == null || year == 0) {
            throw new IllegalStateException("Title and year are required.");
         }
         return new Movie(this);         
      }
   }
}
