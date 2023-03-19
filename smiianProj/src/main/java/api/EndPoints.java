package api;

public interface EndPoints {
    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";   // кажемо: замість нуля підстави перший параметр, який будемо передавати
}
