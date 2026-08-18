package br.pucpr.planet;

public enum PlanetType {
    ROCK("Rochoso"),
    GAS("Gososo"),
    ICE("Gelado"),
    DWARF("Anão");

    private final String label;

    PlanetType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
