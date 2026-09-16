package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableTableData implements TableData {
  private final List<TableDataObserver> observers = new ArrayList<>();

  public void addObserver(TableDataObserver observer) {
    if (observer != null && !observers.contains(observer)) {
      observers.add(observer);
    }
  }

  public void removeObserver(TableDataObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (var observer : List.copyOf(observers)) {
      observer.onDataChanged(this);
    }
  }
}
