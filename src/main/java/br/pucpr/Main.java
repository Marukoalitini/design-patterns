package br.pucpr;

import static br.pucpr.planet.PlanetType.*;
import static br.pucpr.planet.PlanetType.DWARF;
import static br.pucpr.planet.PlanetType.ICE;
import static br.pucpr.table.Theme.LIGHT;

import br.pucpr.planet.DiameterColumn;
import br.pucpr.planet.Planet;
import br.pucpr.planet.SunDistanceAuColumn;
import br.pucpr.planet.SunDistanceKmColumn;
import br.pucpr.planet.TypeColumn;
import br.pucpr.table.Table;
import br.pucpr.table.model.ColumnTableData;
import br.pucpr.table.model.PagedTableData;
import br.pucpr.user.CpfColumn;
import br.pucpr.user.EmailColumn;
import br.pucpr.user.IdColumn;
import br.pucpr.user.NameColumn;
import br.pucpr.user.User;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    final var usuarios = new ArrayList<User>();
    usuarios.add(
        new User(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
    usuarios.add(new User(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
    usuarios.add(
        new User(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
    usuarios.add(new User(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
    usuarios.add(new User(105L, "Lucas Mendes", "lucas@email.com", "12345"));
    usuarios.add(new User(106L, "", "beatriz@email.com", "55566677788"));

    final var userData =
        new ColumnTableData<>(
            usuarios,
            new IdColumn(),
            new NameColumn(),
            new CpfColumn(),
            new EmailColumn());

    System.out.println("IMPRIMINDO USUARIOS (PAGINA 1/2, TAMANHO 3)");
    System.out.println("------------------------------------------");
    final var pagedUsers = new PagedTableData(userData, 3, 1);
    new Table(pagedUsers, LIGHT, true).print();

    final var planetas = new ArrayList<Planet>();
    planetas.add(new Planet("Mercúrio", 4879, 57_910_000L, ROCK));
    planetas.add(new Planet("Vênus", 12104, 108_200_000L, ROCK));
    planetas.add(new Planet("Terra", 12756, 149_600_000L, ROCK));
    planetas.add(new Planet("Marte", 6792, 227_940_000L, ROCK));
    planetas.add(new Planet("Júpiter", 142984, 778_330_000L, GAS));
    planetas.add(new Planet("Saturno", 120536, 1_429_400_000L, GAS));
    planetas.add(new Planet("Urano", 51118, 2_870_990_000L, ICE));
    planetas.add(new Planet("Netuno", 49528, 4_504_300_000L, ICE));
    planetas.add(new Planet("Plutão", 2376, 5_906_380_000L, DWARF));

    final var planetData =
        new ColumnTableData<>(
            planetas,
            new br.pucpr.planet.NameColumn(),
            new DiameterColumn(),
            new SunDistanceKmColumn(),
            new SunDistanceAuColumn(),
            new TypeColumn());

    System.out.println();
    System.out.println("IMPRIMINDO PLANETAS (PAGINA 2/3, TAMANHO 4)");
    System.out.println("------------------------------------------");
    final var pagedPlanets = new PagedTableData(planetData, 4, 2);
    new Table(pagedPlanets).print();
  }
}
