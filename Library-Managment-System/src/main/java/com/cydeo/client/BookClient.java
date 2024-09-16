package com.cydeo.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "https://openlibrary.org/", name = "BOOK-CLIENT")
public interface BookClient {

   // https://openlibrary.org/search.json?q=best+sellers
}
