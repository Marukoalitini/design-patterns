package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public class SunDistanceAuColumn implements ColumnData<Planet> {
  @Override
  public String header() {
    return "%15s".formatted("Dist. sol (ua)");
  }

  @Override
  public String get(Planet planet) {
    return "%,15.2f".formatted(Planet.kmToAu(planet.sunDistanceKm()));
  }
}
