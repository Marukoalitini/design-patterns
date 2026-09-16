package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public class NameColumn implements ColumnData<Planet> {
  @Override
  public String header() {
    return "%-10s".formatted("Nome");
  }

  @Override
  public String get(Planet planet) {
    final var name = planet.name();
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      return name.substring(0, 17) + "...";
    }
    return name;
  }
}
