package com.operantic.demo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Amani on 04/12/2018.
 */
public class RegionBloc {

    private String acronym;

    private String name;

    private List<String> otherAcronyms = new ArrayList<>();

    private List<String> otherNames = new ArrayList<>();

    public RegionBloc() {
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<String> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
}
