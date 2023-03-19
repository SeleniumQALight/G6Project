package api;

public interface EndPoints { // виносфть ендпоінти, щоб їх не дублювати.

    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}"; // за допомогою rest-assured можемо вставляти параметри

}
