package api;


public interface EndPoints { // виносять ендпоінти, щоб їх не дублювати.

    String baseUrl = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}"; // за допомогою rest-assured можемо вставляти параметри

    String LOGIN = baseUrl + "/api/login";

    String CREATE_POST = baseUrl + "/api/create-post";

    String DELETE_POST = baseUrl + "/api/post/{0}";
}
