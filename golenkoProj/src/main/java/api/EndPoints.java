package api;

public interface EndPoints {
    String baseURL = "https://qa-complexapp.onrender.com";
    String POST_BY_USER = baseURL + "/api/postsByAuthor/{0}";
    String LOGIN = baseURL + "/api/login";
    String CREATE_POST = baseURL + "/api/create-post";
    String DELETE_POST = baseURL + "/api/post/{0}";
}
