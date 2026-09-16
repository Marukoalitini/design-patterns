package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public class DiameterColumn implements ColumnData<Planet> {
  @Override
  public String header() {
    return "%10s".formatted("Diâmetro");
  }

  @Override
  public String get(Planet planet) {
    return "%,10.1f".formatted(planet.diameterKm());
  }
}
