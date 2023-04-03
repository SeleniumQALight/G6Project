package api;

public interface EndpointsHomeWorkDemoQA {

    String baseUrl="https://demoqa.com";

    String LOGIN =baseUrl+"/Account/v1/Login";

    String CREATE_USER=baseUrl+"/Account/v1/User";


    String DELETE_BOOKS=baseUrl+"/BookStore/v1/Books?UserId={0}";


    String GET_LIST_OF_BOOKS=baseUrl+"/BookStore/v1/Books";



    String ADD_BOOK_TO_USER=baseUrl+"/BookStore/v1/Books";




}
