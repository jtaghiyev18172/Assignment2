package MainApp.model;

public class Movie {
    private String title;
    private String director;
    private int releaseYear;
    private int runningTime;

    public Movie(String title, String director, int releaseYear, int runningTime) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
    public static Movie fromString(String line) {
        String[] parts = line.split(";");
        if (parts.length == 4) {
            String title = parts[0];
            String director = parts[1];
            int releaseYear = Integer.parseInt(parts[2]);
            int runningTime = Integer.parseInt(parts[3]);
            return new Movie(title, director, releaseYear, runningTime);
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        return title + ";" + director + ";" + releaseYear + ";" + runningTime;
    }
}