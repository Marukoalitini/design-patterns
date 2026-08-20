package br.pucpr.table;

public interface TableData {
    int getCols();
    int getRows();
    String getHeader(int columnIndex);
    String getValue(int rowIndex, int columnIndex);
}
