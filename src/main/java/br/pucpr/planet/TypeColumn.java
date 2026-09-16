package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public class TypeColumn implements ColumnData<Planet> {
  @Override
  public String header() {
    return "%-10s".formatted("Tipo");
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }

  @Override
  public String get(Planet planet) {
    return "%-10s".formatted(formatType(planet.type()));
  }
}
