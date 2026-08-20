package br.pucpr.table;

import br.pucpr.user.Theme;

public class Table {
    public static void print(TableData data, boolean alignRight, Theme theme) {
        if (data == null || data.getRows() == 0) {
            System.out.println("ERRO: Lista vazia ou nula.");
            return;
        }

        final var borderChar = theme.getBorderChar();
        int cols = data.getCols();

        int borderWidth = 1;
        for (int col = 0; col < cols; col++) {
            borderWidth += data.getHeader(col).length() + 3;
        }

        var sb = new StringBuilder();


        sb.repeat(borderChar, borderWidth).append("\n");

        sb.append("|");
        for (int col = 0; col < cols; col++) {
            sb.append(String.format(" %s |", data.getHeader(col)));
        }
        sb.append("\n");

        sb.repeat(borderChar, borderWidth).append("\n");

        for (int row = 0; row < data.getRows(); row++) {
            sb.append("|");
            for (int col = 0; col < cols; col++) {
                String value = data.getValue(row, col);
                if (value == null) {
                    value = "";
                }
                int colWidth = data.getHeader(col).length();
                if (value.length() > colWidth) {
                    value = value.substring(0, colWidth);
                }
                sb.append(String.format(" %-" + colWidth + "s |", value));
            }
            sb.append("\n");
        }

        sb.repeat(borderChar, borderWidth).append("\n");

        if (alignRight) {
            var lines = sb.toString().split("\n");
            for (var line : lines) {
                System.out.println("                    " + line);
            }
        } else {
            System.out.print(sb);
        }
    }
}
