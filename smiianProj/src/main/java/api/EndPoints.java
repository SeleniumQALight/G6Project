package api;

import java.net.URI;

public interface EndPoints {
    String baseUrl = "https://aqa-complexapp.onrender.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";   // кажемо: замість нуля підстави перший параметр, який будемо передавати

    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";
}
