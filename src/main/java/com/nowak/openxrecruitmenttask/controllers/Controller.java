package com.nowak.openxrecruitmenttask.controllers;

import com.nowak.openxrecruitmenttask.calc.Calculates;
import com.nowak.openxrecruitmenttask.dtos.UserInfo;
import com.nowak.openxrecruitmenttask.dtos.users.Geo;
import com.nowak.openxrecruitmenttask.dtos.users.User;


import com.nowak.openxrecruitmenttask.models.UsersDistance;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
public class Controller {

    private final String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "/posts";
    private final String USERS = "/users";

    ResponseEntity<List<UserInfo>> userInfoResponse;
    ResponseEntity<List<User>> usersResponse;
    List<UserInfo> infos;
    List<User> users;

    @PostConstruct
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        userInfoResponse = restTemplate.exchange(
                BASE_API_URL + POSTS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserInfo>>() {
                }
        );
        infos = userInfoResponse.getBody();
        usersResponse = restTemplate.exchange(
                BASE_API_URL + USERS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );
        users = usersResponse.getBody();
    }

    /*
      policzy ile postów napisali userzy i zwróci listę stringów w postaci
      “user_name napisał(a) countpostów"
     */
    @GetMapping("/count")
    public List<String> getCountUsersPosts() {
        Map<Integer, Long> myMap = infos.stream()
                .collect(groupingBy(UserInfo::getUserId, counting()));

        List<String> result = users.stream()
                .map(user -> user.getName() + " napisał " + myMap.get(user.getId()) + " postów.")
                .collect(Collectors.toList());
        return result;
    }

    /*
     sprawdzi czy tytuły postów są unikalne i zwróci listę tytułów które nie są.
     */
    @GetMapping("/unique")
    public List<String> checkIfPostsAreUnique() {
        List<String> getNotDuplicates = Calculates.calculateDuplicates(infos);
        if (getNotDuplicates.isEmpty())
            getNotDuplicates.add("Lista duplikatów jest pusta, jej rozmiar wynosi " + getNotDuplicates.size() + ". Wszystkie tytuły są więc unikalne.");
        return getNotDuplicates;
    }

    /*
    dla każdego użytkownika znajdzie innego użytkownika, który mieszka najbliżej niego
     */
    @GetMapping("/nearest")
    public List<String> getListOfUsersAndNeighbour() {
        Map<Integer, Geo> userGeoMap = users.stream()
                .collect(toMap(User::getId, user -> user.getAddress().getGeo()));

        List<UsersDistance> distanceList = Calculates.calculateDistances(userGeoMap);
        Map<Integer, Integer> mapOfNeighbours = Calculates.findNeighbours(distanceList);
        List<String> nearestNeigbours = mapOfNeighbours.entrySet()
                .stream()
                .map(n -> "Użytkownik o imieniu " + findUserById(n.getKey()).get().getName() + " mieszka najbliżej użytkownika "
                        + findUserById(n.getValue()).get().getName())
                .collect(toList());
        return nearestNeigbours;
    }

    public Optional<User> findUserById(Integer id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
}
























