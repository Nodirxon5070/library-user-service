package com.company.userservice.constants;


public class Constants {

    public static final String EXAMPLE_USER_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data": {
                    "loadId": "Value ...",
                    "customerId": "Value ...",
                    "amount": "Value ...",
                    "status": "Value ..."
                 }
            }
            """;


    public static final String EXAMPLE_USER_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Publisher with  :: id is not found"
            }
            """;
    public static final String EXAMPLE_CARD_SUCCESS = """
            {
                 "success":"true",
                 "code":"0",
                 "message":"OK",
                 "data": {
                    "loadId": "Value ...",
                    "customerId": "Value ...",
                    "amount": "Value ...",
                    "status": "Value ..."
                 }
            }
            """;


    public static final String EXAMPLE_CARD_NOT_FOUND = """
            {
                 "success":"false",
                 "code":"-1",
                 "message":"Publisher with  :: id is not found"
            }
            """;


}


