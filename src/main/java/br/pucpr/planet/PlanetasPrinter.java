package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.ArrayList;

public class PlanetasPrinter implements TableData {
    private ArrayList<Planet> planets;
    private String[] columns = {"Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo"};

    public PlanetasPrinter(ArrayList<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public int getCols() {
        return columns.length;
    }

    @Override
    public int getRows() {
        if (planets == null) {
            return 0;
        }
        return planets.size();
    }

    @Override
    public String getHeader(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.format("%-20s", columns[0]);
            case 1 -> String.format("%-10s", columns[1]);
            case 2 -> String.format("%-15s", columns[2]);
            case 3 -> String.format("%-15s", columns[3]);
            case 4 -> String.format("%-10s", columns[4]);
            default -> "";
        };
    }

    @Override
    public String getValue(int rowIndex, int columnIndex) {
        Planet planet = planets.get(rowIndex);
        if (planet == null) {
            return "";
        }

        return switch (columnIndex) {
            case 0 -> formatName(planet.name());
            case 1 -> String.format("%,10.1f", planet.diameterKm());
            case 2 -> String.format("%,15d", planet.sunDistanceKm());
            case 3 -> String.format("%15.02f", Planet.kmToAu(planet.sunDistanceKm()));
            case 4 -> formatType(planet.type());
            default -> "";
        };
    }

    private static String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return "NÃO INFORMADO";
        }
        if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }
        return name;
    }

    private static String formatType(PlanetType type) {
        return switch (type) {
            case ROCK -> "Rochoso";
            case GAS -> "Gasoso";
            case ICE -> "Gelado";
            case DWARF -> "Anão";
        };
    }
}
