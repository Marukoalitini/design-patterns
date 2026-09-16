package br.pucpr.table.model;

public class PagedTableData extends ObservableTableData implements TableDataObserver {
  private final TableData data;
  private int pageSize;
  private int page;

  public PagedTableData(TableData data, int pageSize, int page) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be greater than 0");
    }
    if (page <= 0) {
      throw new IllegalArgumentException("Page must be greater than 0");
    }
    this.data = data;
    this.pageSize = pageSize;
    this.page = page;

    if (data instanceof ObservableTableData observable) {
      observable.addObserver(this);
    }
  }

  public PagedTableData(TableData data, int pageSize) {
    this(data, pageSize, 1);
  }

  @Override
  public void onDataChanged(TableData source) {
    notifyObservers();
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    if (page <= 0) {
      throw new IllegalArgumentException("Page must be greater than 0");
    }
    if (this.page != page) {
      this.page = page;
      notifyObservers();
    }
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be greater than 0");
    }
    if (this.pageSize != pageSize) {
      this.pageSize = pageSize;
      notifyObservers();
    }
  }

  public int getTotalRows() {
    return data.rowCount();
  }

  public int getTotalPages() {
    final var totalRows = data.rowCount();
    return totalRows == 0 ? 1 : (int) Math.ceil((double) totalRows / pageSize);
  }

  public boolean hasNextPage() {
    return page < getTotalPages();
  }

  public boolean hasPreviousPage() {
    return page > 1;
  }

  public void nextPage() {
    if (hasNextPage()) {
      setPage(page + 1);
    }
  }

  public void previousPage() {
    if (hasPreviousPage()) {
      setPage(page - 1);
    }
  }

  @Override
  public int rowCount() {
    final var totalRows = data.rowCount();
    final var startIndex = (page - 1) * pageSize;
    if (startIndex >= totalRows || startIndex < 0) {
      return 0;
    }
    return Math.min(pageSize, totalRows - startIndex);
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    final var startIndex = (page - 1) * pageSize;
    return data.get(startIndex + row, col);
  }
}
