package com.nowak.openxrecruitmenttask.calc;

import com.nowak.openxrecruitmenttask.dtos.UserInfo;
import com.nowak.openxrecruitmenttask.dtos.users.Geo;
import com.nowak.openxrecruitmenttask.dtos.users.User;
import com.nowak.openxrecruitmenttask.models.UsersDistance;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.StrictMath.sin;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class Calculates {

    private final int EARTH_RADIUS = 6371;

    public List<UsersDistance> calculateDistances(Map<Integer, Geo> userGeoMap) {
        List<UsersDistance> distanceList = new ArrayList<>();
        for (int i = 1; i <= userGeoMap.size(); i++) {
            for (int j = 1; j <= userGeoMap.size(); j++) {
                if (i == j) continue;
                Double dLat = Math.abs(Double.parseDouble(userGeoMap.get(i).getLat()) - Double.parseDouble(userGeoMap.get(j).getLat()));
                Double dLng = Math.abs(Double.parseDouble(userGeoMap.get(i).getLng()) - Double.parseDouble(userGeoMap.get(j).getLng()));
                double a = sin(dLat / 2) * sin(dLat / 2)
                        + Math.cos(Math.abs(Double.parseDouble(userGeoMap.get(j).getLat())))
                        * Math.cos(Math.abs(Double.parseDouble(userGeoMap.get(i).getLat())))
                        * sin(dLng / 2) * sin(dLng / 2);
                double distance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                distance = distance * EARTH_RADIUS;
                distanceList.add(new UsersDistance(i, j, distance));
            }
        }
        return distanceList;
    }

    public Map<Integer, Integer> findNeighbours(List<UsersDistance> distanceList) {
        Double min = 0.0;
        Integer toNeighbour = 0;
        Map<Integer, Integer> mapOfNeighbours = new HashMap<>();
        for (int index = 0; index <= distanceList.get(distanceList.size() - 1).getFrom(); index++) {
            int k = 0;
            min = distanceList.get(k).getDistance();
            for (k = 0; k < distanceList.size(); k++) {
                if (distanceList.get(k).getFrom() == index) {
                    if (min > distanceList.get(k).getDistance()) {
                        min = distanceList.get(k).getDistance();
                        toNeighbour = distanceList.get(k).getTo();
                    }
                    if (k == distanceList.size()) mapOfNeighbours.put(index, toNeighbour);
                }
            }
            if (k == distanceList.size() && index != 0)  mapOfNeighbours.put(index, toNeighbour);
        }
        return mapOfNeighbours;
    }

    public List<String> calculateDuplicates(List<UserInfo> infos) {
        List<String> listNotDuplicates = infos.stream()
                .collect(Collectors.groupingBy(UserInfo::getTitle, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1L)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return listNotDuplicates;
    }

    public List<User> mergeApi(List<User> users, List<UserInfo> infos) {
        Map<Integer, List<UserInfo>> userInfoMap = infos.stream().collect(groupingBy(UserInfo::getUserId));
        for (User user : users) {
            if (userInfoMap.containsKey(user.getId())) user.setPosts(userInfoMap.get(user.getId()));

        }
        return users;
    }

    public List<String> getNearestNeigbours(Map<Integer, Integer> mapOfNeighbours, List<User> users) {
        List<String> nearestNeigbours = mapOfNeighbours.entrySet()
                .stream()
                .map(n -> "Użytkownik o imieniu " + findUserById(n.getKey(), users).get().getName() + " mieszka najbliżej użytkownika "
                        + findUserById(n.getValue(), users).get().getName())
                .collect(toList());
        return nearestNeigbours;
    }

    public Optional<User> findUserById(Integer id, List<User> users) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

}
