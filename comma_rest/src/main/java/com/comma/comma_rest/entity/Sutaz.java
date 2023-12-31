package com.comma.comma_rest.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Nakoniec pretoze ani povodne nie je dokoncene

@Data
public class Sutaz {

    private int id;
    private String nazov;
    private LocalDate odDatum;
    private LocalDate doDatum;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public Sutaz(String nazov, LocalDate odDatum, LocalDate doDatum) {
        this.nazov = nazov;
        this.odDatum = odDatum;
        this.doDatum = doDatum;
    }

    public Sutaz(int id, String nazov, LocalDate odDatum, LocalDate doDatum) {
        this.id = id;
        this.nazov = nazov;
        this.odDatum = odDatum;
        this.doDatum = doDatum;
    }

    public Sutaz(int id, String nazov, LocalDate odDatum) {
        this.id = id;
        this.nazov = nazov;
        this.odDatum = odDatum;
    }

    public boolean jeSutazAktualna() {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.isAfter(odDatum) && currentDate.isBefore(doDatum)) || currentDate.isEqual(doDatum) || currentDate.isEqual(odDatum);
    }

    @Override
    public String toString() {
        String formattedOdDatum = (odDatum != null) ? odDatum.format(DATE_FORMATTER) : "";
        String formattedDoDatum = (doDatum != null) ? doDatum.format(DATE_FORMATTER) : "";
        return nazov + ", " + formattedOdDatum + " - " + formattedDoDatum;
    }

}
