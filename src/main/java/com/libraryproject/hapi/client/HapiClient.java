package com.libraryproject.hapi.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libraryproject.hapi.dto.HapiResponseByIdDto;
import com.libraryproject.hapi.dto.HapiResponseByNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HapiClient {

    private String getBookId(String bookName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hapi-books.p.rapidapi.com/search/" + bookName))
                .header("X-RapidAPI-Key", "1771132312msh25303b917071495p182a4cjsnd31b9aef16b4")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        HapiResponseByNameDto hapiResponse = convertToHapiResponseByNameDto(response.body());
        return String.valueOf(hapiResponse.getBook_id());
    }

    public String getBookDescription(String bookName) throws IOException, InterruptedException {
        bookName = sanitizeBookName(bookName);
        String bookId = getBookId(bookName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hapi-books.p.rapidapi.com/book/" + bookId))
                .header("X-RapidAPI-Key", "1771132312msh25303b917071495p182a4cjsnd31b9aef16b4")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        HapiResponseByIdDto hapiResponse = convertToHapiResponseByIdDto(response.body());
        return hapiResponse.getSynopsis();
    }

    private HapiResponseByNameDto convertToHapiResponseByNameDto(String response){
        Gson gson = new Gson();
        List<HapiResponseByNameDto> hapiResponseDtoList = gson.fromJson(response, new TypeToken<List<HapiResponseByNameDto>>(){}.getType());
        return hapiResponseDtoList.get(0);
    }
    private HapiResponseByIdDto convertToHapiResponseByIdDto(String response){
        Gson gson = new Gson();
        return gson.fromJson(response, new TypeToken<HapiResponseByIdDto>(){}.getType());
    }
    private String sanitizeBookName(String bookName){
        return bookName.toLowerCase().replace(" ", "-");
    }
}
