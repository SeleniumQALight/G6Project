package api;



public interface Endpoints {
    String baseUrl="https://aqa-complexapp.onrender.com";
    String POST_BY_USER=baseUrl+"/api/postsByAuthor/{0}";
    String LOGIN = baseUrl+"/api/login";
    String CREATE_POST = baseUrl+"/api/create-post";
    String DELETE_POST = baseUrl+"/api/post/{0}";
}
