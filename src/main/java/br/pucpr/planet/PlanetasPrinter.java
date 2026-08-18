package br.pucpr.planet;

import br.pucpr.user.Theme;

import java.util.ArrayList;

public class PlanetasPrinter {
    public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
        if (planets == null || planets.isEmpty()) {
            System.out.println("ERRO: Lista de planetas vazia ou nula.");
            return;
        }
        final var borderChar = theme.getBorderChar();

        // Borda superior e cabeçalho
        final var BORDER_WIDTH = 91;
        var sb = new StringBuilder();
        sb.repeat(borderChar, BORDER_WIDTH).append("\n");
        sb.append(
                String.format(
                        "| %-20s | %15s | %15s | %15s | %-10s |%n",
                        "Nome",
                        "Diâmetro",
                        "Dist. sol (km)",
                        "Dist. sol (ua)",
                        "Tipo"));
        for (var planet : planets) {
            if (planet == null) {
                continue;
            }
            sb.append(
                    String.format(
                            "| %-20s | %15s | %15s | %15s | %-10s |%n",
                            formatName(planet),
                            formatDiameter(planet.diameterKm()),
                            formatSunDistanceKm(planet.sunDistanceKm()),
                            formatSunDistanceUa(planet.sunDistanceKm()),
                            formatType(planet.type())));
        }

        // Borda inferior
        sb.repeat(borderChar, BORDER_WIDTH).append("\n");

        // Espaçamento
        if (alignRight) {
            var lines = sb.toString().split("\n");
            for (var line : lines) {
                System.out.println("                    " + line);
            }
        } else {
            System.out.print(sb);
        }

    }

    private static String formatName(Planet planet) {
        var name = planet.name();
        if (name == null || name.isEmpty()) {
            return "NÃO INFORMADO";
        }
        if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }
        return name;
    }

    private static String formatDiameter(double diameterKm) {
        return String.format("%,.1f", diameterKm);
    }

    private static String formatSunDistanceKm(long sunDistanceKm) {
        return String.format("%,d", sunDistanceKm);
    }

    private static String formatSunDistanceUa(long sunDistanceKm) {
        final double AU_IN_KM = 149_597_870.7;
        return String.format("%,.2f", sunDistanceKm / AU_IN_KM);
    }

    private static String formatType(PlanetType type) {
        if (type == null) {
            return "NÃO INFORMADO";
        }
        return type.label();
    }
}