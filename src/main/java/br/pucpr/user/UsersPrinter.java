package br.pucpr.user;

import br.pucpr.table.TableData;
import java.util.ArrayList;

public class UsersPrinter implements TableData {
    private ArrayList<User> users;
    private boolean maskCpf;
    private String[] columns = {"ID", "NOME", "EMAIL", "CPF"};

    public UsersPrinter(ArrayList<User> users, boolean maskCpf) {
        this.users = users;
        this.maskCpf = maskCpf;
    }

    public UsersPrinter(ArrayList<User> users) {
        this(users, false);
    }

    @Override
    public int getCols() {
        return columns.length;
    }

    @Override
    public int getRows() {
        if (users == null) {
            return 0;
        }
        return users.size();
    }

    @Override
    public String getHeader(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.format("%-5s", columns[0]);
            case 1 -> String.format("%-20s", columns[1]);
            case 2 -> String.format("%-22s", columns[2]);
            case 3 -> String.format("%-14s", columns[3]);
            default -> "";
        };
    }

    @Override
    public String getValue(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        if (user == null) {
            return "";
        }

        return switch (columnIndex) {
            case 0 -> formatId(user.id());
            case 1 -> formatName(user);
            case 2 -> validateAndFormatEmail(user.email());
            case 3 -> formatCpf(user.cpf(), maskCpf);
            default -> "";
        };
    }

    private static String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private static String formatCpf(String cpf, boolean mask) {
        if (cpf == null || cpf.length() != 11) {
            return "CPF INVÁLIDO";
        }
        if (mask) {
            return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        }
        return cpf.substring(0, 3)
            + "."
            + cpf.substring(3, 6)
            + "."
            + cpf.substring(6, 9)
            + "-"
            + cpf.substring(9, 11);
    }

    private static String validateAndFormatEmail(String email) {
        return email == null || !email.contains("@") ? "INVÁLIDO" : email;
    }

    private static String formatName(User user) {
        var name = user.name();
        if (name == null || name.isEmpty()) {
            return "NÃO INFORMADO";
        }
        if (name.length() > 20) {
            name = name.substring(0, 17) + "...";
        }
        return name;
    }
}
