package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public class SunDistanceKmColumn implements ColumnData<Planet> {
  @Override
  public String header() {
    return "%15s".formatted("Dist. sol (km)");
  }

  @Override
  public String get(Planet planet) {
    return "%,15d".formatted(planet.sunDistanceKm());
  }
}
