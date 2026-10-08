package uk.msph.hmhome;
import java.time.*;
import java.util.*;
public final class PrayerClock {
    public static final ZoneId LONDON = ZoneId.of("Europe/London");
    public static final String[] KEYS = {"fajr", "zohr", "maghrib"};
    public static final String[] LABELS = {"Fajr", "Zohr", "Maghrib"};
    public static String endpoint(LocalDate date) {
        return String.format(Locale.ROOT,"https://api.poc.hujjat.org/salaat/city/london/year/%04d/month/%02d/day/%02d",date.getYear(),date.getMonthValue(),date.getDayOfMonth());
    }
    public static LocalTime time(String value) {
        if(value==null || !value.matches("([01]\\d|2[0-3]):[0-5]\\d")) throw new IllegalArgumentException("Invalid prayer time");
        return LocalTime.parse(value);
    }
    public static final class Next {
        public final String label; public final ZonedDateTime at; public final long seconds;
        Next(String label,ZonedDateTime at,ZonedDateTime now) { this.label=label;this.at=at;this.seconds=Math.max(0,Duration.between(now,at).getSeconds()); }
        public String countdown() { long m=(seconds+59)/60;return m>=60?(m/60)+"h "+(m%60)+"m":m+"m"; }
    }
    public static Next next(ZonedDateTime instant, Map<String,String> today, Map<String,String> tomorrow) {
        ZonedDateTime now=instant.withZoneSameInstant(LONDON);
        for(int i=0;i<KEYS.length;i++) { String value=today.get(KEYS[i]); if(value==null)continue;
            ZonedDateTime at=ZonedDateTime.of(now.toLocalDate(),time(value),LONDON);
            if(!at.isBefore(now))return new Next(LABELS[i],at,now);
        }
        String fajr=tomorrow.get("fajr");return fajr==null?null:new Next("Fajr",ZonedDateTime.of(now.toLocalDate().plusDays(1),time(fajr),LONDON),now);
    }
}
