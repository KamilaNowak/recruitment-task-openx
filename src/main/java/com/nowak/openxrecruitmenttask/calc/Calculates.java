package com.nowak.openxrecruitmenttask.calc;

import com.nowak.openxrecruitmenttask.dtos.UserInfo;
import com.nowak.openxrecruitmenttask.dtos.users.Geo;
import com.nowak.openxrecruitmenttask.models.UsersDistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.StrictMath.sin;

public class Calculates {

    private static final int EARTH_RADIUS = 6371;

    public static List<UsersDistance> calculateDistances(Map<Integer, Geo> userGeoMap) {

        System.out.println("rozmiar : "+ userGeoMap.size());
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

    public static Map<Integer, Integer> findNeighbours(List<UsersDistance> distanceList) {
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
                    if (k == distanceList.size()) {
                        mapOfNeighbours.put(index, toNeighbour);
                    }
                }
            }
            if (k == distanceList.size() && index != 0) {
                mapOfNeighbours.put(index, toNeighbour);
            }
        }
        return mapOfNeighbours;
    }

    public static List<String> calculateDuplicates(List<UserInfo> infos) {
        List<String> listNotDuplicates = infos.stream()
                .collect(Collectors.groupingBy(UserInfo::getTitle, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1L)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        return listNotDuplicates;
    }

}
