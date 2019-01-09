package com.noel_inc.crudappinterview.network;

public class ApiUtils {


        public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

        public static GetpostService getPostService() {
            return RetrofitClient.getClient(BASE_URL).create(GetpostService.class);
        }
}
