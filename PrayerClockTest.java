import uk.msph.hmhome.PrayerClock;
import java.time.*;
import java.util.*;
public class PrayerClockTest {
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 public static void main(String[] args){
  Map<String,String> today=new HashMap<>(),tomorrow=new HashMap<>();today.put("fajr","05:46");today.put("zohr","12:54");today.put("maghrib","18:37");tomorrow.put("fajr","05:47");
  check(PrayerClock.endpoint(LocalDate.of(2026,3,9)).endsWith("/year/2026/month/03/day/09"),"Padded URL");
  PrayerClock.Next n=PrayerClock.next(ZonedDateTime.of(2026,10,8,18,0,0,0,PrayerClock.LONDON),today,tomorrow);check(n.label.equals("Maghrib")&&n.seconds==2220,"Next prayer uses Maghrib");
  n=PrayerClock.next(ZonedDateTime.of(2026,10,8,22,0,0,0,PrayerClock.LONDON),today,tomorrow);check(n.label.equals("Fajr")&&n.at.toLocalDate().equals(LocalDate.of(2026,10,9)),"Tomorrow rollover");
  n=PrayerClock.next(ZonedDateTime.of(2026,10,8,5,46,0,0,PrayerClock.LONDON),today,tomorrow);check(n.seconds==0,"Exact prayer instant");
  check(PrayerClock.next(ZonedDateTime.of(2026,10,8,22,0,0,0,PrayerClock.LONDON),today,Collections.emptyMap())==null,"Missing tomorrow never invents time");
  n=PrayerClock.next(ZonedDateTime.of(2026,10,24,22,0,0,0,PrayerClock.LONDON),today,tomorrow);check(n.seconds==8*3600+47*60,"DST fall-back countdown uses elapsed time");
  try{PrayerClock.time("25:10");throw new AssertionError("Invalid accepted");}catch(IllegalArgumentException expected){}
  System.out.println("Passed seven prayer/date/DST checks.");
 }
}
