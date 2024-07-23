package com.snow.previsaotempo.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private Location location;
    private Current current;
    private Forecast forecast;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        private String tz_id;
        private String localtime;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        private double temp_c;
        private Condition condition;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Condition {
            private String text;
            private String icon;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Forecast {
        private List<ForecastDay> forecastday;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ForecastDay {
            private String date;
            private Day day;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Day {
                private double maxtemp_c;
                private double mintemp_c;
                private Condition condition;

                @Data
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Condition {
                    private String text;
                    private String icon;
                }
            }
        }
    }
}
