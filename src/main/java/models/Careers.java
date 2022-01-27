package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Careers {

    DANGER, KNOWLEDGE, PASSION;

    private static Map<Careers, List<String>> collegeCareers = Map.of(
            DANGER, List.of("ANTARCTIC EXPLORER", "ASTRONAUT", "FIGHTER PILOT"),
            KNOWLEDGE, List.of("DOCTOR", "PROFESSOR", "ACCOUNTANT"),
            PASSION, List.of("CHARITY WORKER", "MUSEUM CURATOR", "PHILOSOPHER")
    );
    private static Map<Careers, List<String>> nonCollegeCareers = Map.of(
            DANGER, List.of("UNDERWATER SEA WELDER", "TREE CUTTER", "MARINE"),
            KNOWLEDGE, List.of("DATA ENTRY", "PLUMBER", "SOFTWARE ENGINEER"),
            PASSION, List.of("SCULPTOR", "MUSICIAN", "PERFORMER")
    );

    public static Map<Careers, List<String>> getCollegeCareers(){
        return collegeCareers;
    }
    public static Map<Careers, List<String>> getNonCollegeCareers(){
        return nonCollegeCareers;
    }

}

